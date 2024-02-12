package yongtaek.miniproject.justchatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yongtaek.miniproject.justchatting.domain.User;
import yongtaek.miniproject.justchatting.exception.UserNotFoundException;
import yongtaek.miniproject.justchatting.model.CreateUserRequestDto;
import yongtaek.miniproject.justchatting.model.ReadUserRequestDto;
import yongtaek.miniproject.justchatting.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User saveUser(CreateUserRequestDto requestDto){
        String encodedPassword = this.passwordEncoding(requestDto.getPassword());
        return userRepository.save(requestDto.toEntity(encodedPassword));
    }

    public User findUser(ReadUserRequestDto requestDto){
        String encodedPassword = this.passwordEncoding(requestDto.getUserId());
        return userRepository.findUserByUserIdAndPassword(requestDto.getUserId(),encodedPassword)
                .orElseThrow(()-> new UserNotFoundException("user not found!"));
    }

    private String passwordEncoding(String originalPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(originalPassword);
    }

}
