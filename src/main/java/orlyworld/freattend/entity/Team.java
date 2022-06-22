package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    private String tel;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Admin> admins = new ArrayList<>();

    //== 생성 메서드 ==//
    public static Team createTeam(String name, String tel) {
        Team team = new Team();
        team.name = name;
        team.tel = tel;

        return team;
    }

}
