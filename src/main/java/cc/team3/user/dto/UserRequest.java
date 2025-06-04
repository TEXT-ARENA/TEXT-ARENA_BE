package cc.team3.user.dto;

public class UserRequest {

    public record UserCreateRequestDTO(String username, String password) {

    }
}
