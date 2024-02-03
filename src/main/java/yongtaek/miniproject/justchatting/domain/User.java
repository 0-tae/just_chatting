package yongtaek.miniproject.justchatting.domain;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String userId;

    @Column
    private String username;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // 자료형이 주어졌을 때, 필요한 정보만 데이터로 작성하는 것을 지정
    private LocalDateTime createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;
}
