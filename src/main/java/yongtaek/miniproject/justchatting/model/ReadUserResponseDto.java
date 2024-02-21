package yongtaek.miniproject.justchatting.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class ReadUserResponseDto {
    private String userId;
    private String username;
}