package yongtaek.miniproject.justchatting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yongtaek.miniproject.justchatting.domain.User;
import yongtaek.miniproject.justchatting.exception.PasswordNotCorrectedException;
import yongtaek.miniproject.justchatting.exception.UserNotFoundException;
import yongtaek.miniproject.justchatting.model.CreateUserRequestDto;
import yongtaek.miniproject.justchatting.model.ReadUserRequestDto;
import yongtaek.miniproject.justchatting.model.UpdateUsernameRequestDto;
import yongtaek.miniproject.justchatting.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User saveUser(CreateUserRequestDto requestDto){
        String encodedPassword = this.passwordEncoding(requestDto.getPassword());
        return userRepository.save(requestDto.toEntity(encodedPassword));
    }

    public User findUser(ReadUserRequestDto requestDto){
        String encodedPassword = this.passwordEncoding(requestDto.getPassword());
        if (!encoder.matches(requestDto.getPassword(),encodedPassword)){
            throw new PasswordNotCorrectedException();
        }

        return userRepository.findUserByUserId(requestDto.getUserId())
                .orElseThrow(()-> new UserNotFoundException("user not found!"));
    }

    public User updateUsername(UpdateUsernameRequestDto dto){
        // fetch User from DB
        User user = userRepository.findUserByUserId(dto.getUserId())
                .orElseThrow(()->new UserNotFoundException("user not found!"));

        // change username field to given username
        user.setUsername(dto.getUsername());

        // update User Entity
        userRepository.saveAndFlush(user);

        return user;
    }

    public User removeUser(String userId){
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(()->new UserNotFoundException("user not found"));

        userRepository.delete(user);

        return user;
    }

    private String passwordEncoding(String originalPassword){
        return encoder.encode(originalPassword);
    }

}
