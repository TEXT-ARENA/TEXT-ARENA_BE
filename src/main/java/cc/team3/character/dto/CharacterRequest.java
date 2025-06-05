package cc.team3.character.dto;

public class CharacterRequest {
    public record CharacterCreateRequestDTO(String characterName, String description) {
    }
}
