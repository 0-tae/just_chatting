package yongtaek.miniproject.justchatting.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UpdateUsernameRequestDto {
    private String userId;
    private String username;
}
