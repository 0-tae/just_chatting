package yongtaek.miniproject.justchatting.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ReadUserRequestDto {
    private String userId;
    private String password;
}
