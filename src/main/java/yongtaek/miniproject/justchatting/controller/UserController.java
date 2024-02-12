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

        // TODO: ResponseAdvice를 통해서 Response에 ok field를 추가하기
        return ResponseEntity.ok(CreateUserResponseDto.builder().userId(createdUser.getUserId())
                .username(createdUser.getUsername())
                .build());
    }

    @PostMapping
    public ResponseEntity<ReadUserResponseDto> readUserForLogin
            (@RequestBody ReadUserRequestDto requestDto, HttpSession session){
        User user =  userService.findUser(requestDto);

        // 유저 세션 생성
        session.setAttribute("userId", user.getUserId());
        session.setMaxInactiveInterval(60 * 60);

        return ResponseEntity.ok(ReadUserResponseDto.builder().userId(user.getUserId())
                .username(user.getUsername())
                .build());
    }

    /*
        TODO:
         세션 만료 몇 초전에 실행 되어야 할 함수인데, 스케줄링을 해야할까?
         일단 JWT와 세션 사이에서 고민 좀 해보기,
         JWT로 Header에서 rule을 확인할 수 있다면. 인터셉터에서 유저별 접근 가능 여부를 구현할 수 있다.
    */
    @PatchMapping("/refresh/{userId}")
    public ResponseEntity sessionRefresh(HttpSession session, @PathVariable String userId){
        // isNew() -> ValidInterval인지 확인하는 함수
        // 세션을 만료시키고, 다시 생성
        if(session.isNew()){
            session.invalidate();
            session.setAttribute("userId", userId);
            session.setMaxInactiveInterval(60 * 60);
        }

        // TODO: 응답 타입 생각해 볼 것
        return ResponseEntity.ok(true);
    }
}
