package yongtaek.miniproject.justchatting.domain;


import jakarta.persistence.*;

@Entity
@Table
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn()
    private User chattedUser;

    @Column // 채팅 최대길이 제한 255byte
    private String content;

    @ManyToOne
    @JoinColumn()
    private Room chattedRoom;

}
