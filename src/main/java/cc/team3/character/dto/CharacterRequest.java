package cc.team3.character.dto;

public class CharacterRequest {
    public record CharacterCreateRequestDTO(String characterName, String description) {
    }

    public record CreateEquipmentRequestDTO(String equipmentType, String equipmentName, String description) {
    }

    public record RecordBattleRequestDTO(Long winnerId, Long loserId) {
    }
}
