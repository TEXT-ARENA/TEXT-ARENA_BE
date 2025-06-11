package cc.team3.user.service;

import cc.team3.global.apiPayload.exception.GeneralException;
import cc.team3.global.apiPayload.status.ErrorStatus;
import cc.team3.user.domain.User;
import cc.team3.user.dto.UserRequest;
import cc.team3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(UserRequest.UserCreateRequestDTO request) {
        User user = User.builder()
                .username(request.username())
                .password(request.password())
                .build();

        userRepository.save(user);
        return user.getUserId();
    }

    @Transactional
    public Long loginOrRegister(UserRequest.UserLoginRequestDTO request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.username());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (!user.getPassword().equals(request.password())) {
                throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
            }
            return user.getUserId();
        }
        else {
            UserRequest.UserCreateRequestDTO createRequest = new UserRequest.UserCreateRequestDTO(
                request.username(),
                request.password()
            );

            return saveUser(createRequest);
        }
    }
}
