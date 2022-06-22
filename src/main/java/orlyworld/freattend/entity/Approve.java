package orlyworld.freattend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Approve {

    @Id
    @Column(name = "APPROVE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApproveStatus approveStatus;

    private String comment;

    // 식별관계
    // TODO 식별관계 테스트 코드 작성
    @MapsId
    @OneToOne
    @JoinColumn(name = "REOCRD_ID")
    private Record record;

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private Admin admin;

    //== 생성 메서드 ==//


    //== 비즈니스 메서드 ==//

    /**
     * 초기 승인 객체를 생성
     */
    public static Approve initApprove(Record record) {

        Approve approve = new Approve();

        approve.record = record;
        approve.approveStatus = ApproveStatus.WAIT;

        return approve;
    }

    /**
     * 승인 요청을 처리
     */
    public void answerApprove(Admin admin, ApproveStatus status, String comment) {
        this.admin = admin;
        this.approveStatus = status;
        this.comment = comment;

        // 연관관게 편의 메서드
        if (this.admin != null) {
            this.admin.getApproves().remove(this);
        }

        admin.getApproves().add(this);
    }



}
