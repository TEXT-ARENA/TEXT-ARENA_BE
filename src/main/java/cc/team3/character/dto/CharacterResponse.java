package cc.team3.character.dto;

import cc.team3.character.domain.Effect;
import cc.team3.character.domain.Equipment;

import java.time.LocalDateTime;
import java.util.List;

public class CharacterResponse {
    public record CharacterDetailsResponseDTO(
            Long characterId,
            String name,
            Integer hp,
            String hp_reason,
            Integer exp,
            Integer level,
            Integer attack,
            Integer defense,
            Double criticalChance,
            Double criticalDamage,
            Integer speed,
            Double dodgeChance,
            Double accuracy,
            List<Equipment> equipments,
            List<String> statusEffects,
            Integer wins,
            Integer losses
    ) {}

    public record EquipmentDTO(
            String bonusType,
            Double bonusValue,
            List<Effect> effects,
            String image
    ) {
    }

    public record CharacterCreateResponseDTO(
            Integer hp,
            String hp_reason,
            Integer attack,
            String attack_reason,
            Integer defense,
            String defense_reason,
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
            Integer level,
            Integer wins,
            Integer losses
    ) {}

    public record DeleteCharacterResultDTO (
            Long characterId,
            LocalDateTime deletedAt
    ) {}

    public record ReadEquipmentsResultDTO(
            String name,
            String type,
            String bonusType,
            Object bonusValue,
            List<Effect> effects
    ) {}
}
