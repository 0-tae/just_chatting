package yongtaek.miniproject.justchatting.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yongtaek.miniproject.justchatting.domain.User;
import yongtaek.miniproject.justchatting.model.CreateUserRequestDto;
import yongtaek.miniproject.justchatting.model.CreateUserResponseDto;
import yongtaek.miniproject.justchatting.model.ReadUserRequestDto;
import yongtaek.miniproject.justchatting.model.ReadUserResponseDto;
import yongtaek.miniproject.justchatting.service.UserService;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto){
        User createdUser = userService.saveUser(requestDto);

        // TODO: ResponseAdvice�� ���ؼ� Response�� ok field�� �߰��ϱ�
        return ResponseEntity.ok(CreateUserResponseDto.builder().userId(createdUser.getUserId())
                .username(createdUser.getUsername())
                .build());
    }

    @PostMapping
    public ResponseEntity<ReadUserResponseDto> readUserForLogin
            (@RequestBody ReadUserRequestDto requestDto, HttpSession session){
        User user =  userService.findUser(requestDto);

        // ���� ���� ����
        session.setAttribute("userId", user.getUserId());
        session.setMaxInactiveInterval(60 * 60);

        return ResponseEntity.ok(ReadUserResponseDto.builder().userId(user.getUserId())
                .username(user.getUsername())
                .build());
    }

    /*
        TODO:
         ���� ���� �� ������ ���� �Ǿ�� �� �Լ��ε�, �����ٸ��� �ؾ��ұ�?
         �ϴ� JWT�� ���� ���̿��� ��� �� �غ���,
         JWT�� Header���� rule�� Ȯ���� �� �ִٸ�. ���ͼ��Ϳ��� ������ ���� ���� ���θ� ������ �� �ִ�.
    */
    @PatchMapping("/refresh/{userId}")
    public ResponseEntity sessionRefresh(HttpSession session, @PathVariable String userId){
        // isNew() -> ValidInterval���� Ȯ���ϴ� �Լ�
        // ������ �����Ű��, �ٽ� ����
        if(session.isNew()){
            session.invalidate();
            session.setAttribute("userId", userId);
            session.setMaxInactiveInterval(60 * 60);
        }

        // TODO: ���� Ÿ�� ������ �� ��
        return ResponseEntity.ok(true);
    }
}
