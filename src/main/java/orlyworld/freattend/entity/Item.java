package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    //== 생성 메서드 ==//

    public static Item createItem(ItemCategory category, Member member, Date deadline, int stockQuantity, String description) {

        Item item = new Item();

        item.category = category;
        item.member = member;
        item.deadline = deadline;
        item.stockQuantity = stockQuantity;
        item.description = description;

        member.getItems().add(item);

        return item;
    }

    //== 비지니스 메서드 ==//

    public void useItem() {
        this.stockQuantity--;
    }

    public void cancelItem() {
        this.stockQuantity++;
    }
}
