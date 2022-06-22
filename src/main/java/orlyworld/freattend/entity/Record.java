package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "RECORD_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date useDate;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @OneToOne(mappedBy = "record")
    private Approve approve;

    //== 생성 메서드 ==//

    public static Record createRecord(Member member, Date useDate, Item item) {

        Record record = new Record();

        record.member = member;
        record.useDate = useDate;
        record.approve = Approve.initApprove(record);
        record.item = item;

        item.useItem();
        member.getRecords().add(record);

        return record;
    }

    //== 비즈니스 메서드 ==//

    /**
     * Member의 요청(record)를 처리
     */
    public void answerRecord(Admin admin, ApproveStatus status, String comment) {

        if (status == ApproveStatus.REJECTED || status == ApproveStatus.CANCEL) {
            item.cancelItem();
        }
        this.approve.answerApprove(admin, status, comment);
    }

}
