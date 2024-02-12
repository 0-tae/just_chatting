package yongtaek.miniproject.justchatting.model;


import lombok.*;
import yongtaek.miniproject.justchatting.domain.User;

@Getter
@ToString
@AllArgsConstructor
public class CreateUserRequestDto {
    private String userId;
    private String password;
    private String username;

    public User toEntity(String encodedPassword){
        return User.builder()
                .userId(userId)
                .password(encodedPassword)
                .username(username)
                .build();
    }
}
