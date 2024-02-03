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
    @Temporal(TemporalType.TIMESTAMP) // �ڷ����� �־����� ��, �ʿ��� ������ �����ͷ� �ۼ��ϴ� ���� ����
    private LocalDateTime createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deletedAt;
}
