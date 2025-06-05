package cc.team3.character.dto;

import cc.team3.character.domain.Effect;
import cc.team3.character.domain.Equipment;

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
            List<Equipment> equipments,
            List<String> statusEffects
    ) {}

    public record EquipmentDTO(
            String name,
            String type,
            String bonusType,
            Integer bonusValue,
            List<Effect> effects
    ) {
    }

    public record CharacterCreateResponseDTO(
            String name,
            Integer hp,
            String hpReason,
            Integer attack,
            Integer defense,
            Double criticalChance,
            Double criticalDamage,
            Integer speed,
            Double dodgeChance,
            Double accuracy,
            List<String> statusEffect
    ) {
    }
}
