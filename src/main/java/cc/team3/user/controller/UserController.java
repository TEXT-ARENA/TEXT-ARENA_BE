package cc.team3.user.controller;

import cc.team3.global.apiPayload.ApiResponse;
import cc.team3.user.dto.UserRequest;
import cc.team3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<Long> loginOrRegister(@RequestBody UserRequest.UserLoginRequestDTO request) {
        return ApiResponse.onSuccess(userService.loginOrRegister(request));
    }
}
