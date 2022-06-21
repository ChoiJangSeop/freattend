package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    private String tel;

    private String email;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Record> records = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    //== 생성 메서드 ==//

    public static Member createMember(String name, String tel, String email, Team team) {
        Member member = new Member();

        member.name = name;
        member.tel = tel;
        member.email = email;

        member.setTeam(team);

        return member;
    }


    //== 연관관계 편의 메서드 ==//

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }
}
