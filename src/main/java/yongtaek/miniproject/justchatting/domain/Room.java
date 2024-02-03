package yongtaek.miniproject.justchatting.domain;


import jakarta.persistence.*;

@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
