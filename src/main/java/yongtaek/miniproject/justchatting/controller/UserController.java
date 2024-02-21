package yongtaek.miniproject.justchatting.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yongtaek.miniproject.justchatting.domain.User;
import yongtaek.miniproject.justchatting.model.*;
import yongtaek.miniproject.justchatting.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto){
        /*
        *
        * User Create
        *
        * */
        User createdUser = userService.saveUser(requestDto);

        // TODO: implement ResponseAdvice for ResponseObject to add ok field
        return ResponseEntity.ok(CreateUserResponseDto.builder().userId(createdUser.getUserId())
                .username(createdUser.getUsername())
                .build());
    }

    @PostMapping
    public ResponseEntity<ReadUserResponseDto> readUserForLogin
            (@RequestBody ReadUserRequestDto requestDto, HttpSession session){
        /*
         *
         * User Login
         *
         * */
        User user =  userService.findUser(requestDto);

        // ???? ???? ????
        session.setAttribute("userId", user.getUserId());
        session.setMaxInactiveInterval(60 * 60);

        return ResponseEntity.ok(ReadUserResponseDto.builder().userId(user.getUserId())
                .username(user.getUsername())
                .build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId){
        /*
         *
         * User delete
         *
         * */
        User user = userService.removeUser(userId);
        return ResponseEntity.ok(DeleteUserResponseDto.builder().userId(userId).build());
    }

    @PutMapping
    public ResponseEntity updateUsername(@RequestBody UpdateUsernameRequestDto dto){
        /*
         *
         * Update username
         *
         * */
        User user = userService.updateUsername(dto);
        return ResponseEntity.ok(UpdateUsernameResponseDto.builder()
                .userId(dto.getUserId())
                .previousUsername(dto.getUsername())
                .newUsername(user.getUsername()).build());
    }

    /*
        TODO:
         Session? JWT? 고려해보기
    */
    @PatchMapping("/refresh/{userId}")
    public ResponseEntity sessionRefresh(HttpSession session, @PathVariable String userId){
        /*
         *
         * User's session refresh
         *
         * */
        if(session.isNew()){
            session.invalidate();
            session.setAttribute("userId", userId);
            session.setMaxInactiveInterval(60 * 60);
        }

        return ResponseEntity.ok(true);
    }
}
