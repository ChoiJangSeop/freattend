package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "ADMIN_ID")
    private Long id;

    private String name;

    private String tel;

    private String email;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "admin")
    private List<Approve> approves = new ArrayList<>();

    //== 생성 메서드 ==//

    public static Admin createAdmin(String name, String tel, String email, Team team) {
        Admin admin = new Admin();

        admin.name = name;
        admin.tel = tel;
        admin.email = email;

        admin.setTeam(team);

        return admin;
    }

    //== 연관관계 편의 메서드 ==//

    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getAdmins().remove(this);
        }

        this.team = team;
        team.getAdmins().add(this);
    }
}
