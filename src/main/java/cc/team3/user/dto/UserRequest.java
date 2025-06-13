package cc.team3.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

    public record UserCreateRequestDTO(String username, String password) {

    }

    public record UserLoginRequestDTO(
        String username,
        String password
    ) {
        
    }
}
