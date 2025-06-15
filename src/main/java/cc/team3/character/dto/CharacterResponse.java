package cc.team3.character.dto;

import cc.team3.character.domain.Effect;
import cc.team3.character.domain.Equipment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class CharacterResponse {
    public record CharacterDetailsResponseDTO(
            Long characterId,
            String name,
            Integer hp,
            String hp_reason,
            Integer attack,
            Integer defense,
            Double criticalChance,
            Double criticalDamage,
            Integer speed,
            Double dodgeChance,
            Double accuracy,
            List<Equipment> equipments,
            List<String> statusEffects
    ) {}

    public record EquipmentDTO(
            String name,
            String bonusType,
            Integer bonusValue,
            List<Effect> effects
    ) {
    }

    public record CharacterCreateResponseDTO(
            Integer hp,
            String hp_reason,
            Integer attack,
            Integer defense,
            Double criticalChance,
            Double criticalDamage,
            Integer speed,
            Double dodgeChance,
            Double accuracy
    ) {
    }

    public record RecordBattleResponseDTO(Long winnerId, Long loserId) {
    }
  
    public record ReadCharacterListDTO(
            Long characterId,
            String name,
            Integer hp,
            Integer attack,
            Integer defense,
            Integer exp,
            Integer wins,
            Integer loses
    ) {}

    public record DeleteCharacterResultDTO (
            Long characterId,
            LocalDateTime deletedAt
    ) {}

}
