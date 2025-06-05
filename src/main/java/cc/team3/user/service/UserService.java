package cc.team3.user.service;

import cc.team3.user.domain.User;
import cc.team3.user.dto.UserRequest;
import cc.team3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
