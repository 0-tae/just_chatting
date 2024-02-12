package yongtaek.miniproject.justchatting.model;


import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class CreateUserResponseDto {
    private String userId;
    private String username;
}