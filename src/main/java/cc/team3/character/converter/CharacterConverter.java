package cc.team3.character.converter;

import cc.team3.character.domain.Character;
import cc.team3.character.domain.Equipment;
import cc.team3.character.domain.enums.EquipmentType;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.user.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CharacterConverter {
    public static CharacterResponse.CharacterDetailsResponseDTO toCharacterDetailsResponseDTO(Character character, List<Equipment> equipmentList) {
        return new CharacterResponse.CharacterDetailsResponseDTO(
                character.getCharacterId(),
                character.getCharacterName(),
                character.getHp(),
                character.getHp_reason(),
                character.getExp(),
                character.getLevel(),
                character.getAttack(),
                character.getAttack_reason(),
                character.getDefense(),
                character.getDefense_reason(),
                character.getCriticalChance(),
                character.getCritical_chance_reason(),
                character.getCriticalDamage(),
                character.getCritical_damage_reason(),
                character.getSpeed(),
                character.getSpeed_reason(),
                character.getDodgeChance(),
                character.getDodge_chance_reason(),
                character.getAccuracy(),
                character.getAccuracy_reason(),
                equipmentList,
                character.getStatusEffects(),
                character.getWins(),
                character.getLosses()
        );
    }

    public static Character toCharacter(User user, CharacterResponse.CharacterCreateResponseDTO request, String characterName) {
        return Character.builder()
                .user(user)
                .characterName(characterName)
                .hp(request.hp())
                .hp_reason(request.hp_reason())
                .attack(request.attack())
                .attack_reason(request.attack_reason())
                .defense(request.defense())
                .defense_reason(request.defense_reason())
                .criticalChance(request.criticalChance())
                .critical_chance_reason(request.critical_chance_reason())
                .criticalDamage(request.criticalDamage())
                .critical_damage_reason(request.critical_damage_reason())
                .speed(request.speed())
                .speed_reason(request.speed_reason())
                .dodgeChance(request.dodgeChance())
                .dodge_chance_reason(request.dodge_chance_reason())
                .accuracy(request.accuracy())
                .accuracy_reason(request.accuracy_reason())
                .statusEffects(new ArrayList<>())
                .build();
    }

    public static CharacterResponse.ReadCharacterListDTO toReadCharacterListDTO(Character character) {
        return new CharacterResponse.ReadCharacterListDTO(
                character.getCharacterId(),
                character.getCharacterName(),
                character.getHp(),
                character.getAttack(),
                character.getDefense(),
                character.getCriticalChance(),
                character.getCriticalDamage(),
                character.getSpeed(),
                character.getDodgeChance(),
                character.getAccuracy(),
                character.getExp(),
                character.getLevel(),
                character.getWins(),
                character.getLosses());
    }

    public static CharacterResponse.DeleteCharacterResultDTO toDeleteCharacterResultDTO(Long characterId) {
        return new CharacterResponse.DeleteCharacterResultDTO(
                characterId,
                LocalDateTime.now()
        );
    }

    public static CharacterResponse.ReadEquipmentsResultDTO toReadEquipmentsResultDTO(Equipment equipment) {
        return new CharacterResponse.ReadEquipmentsResultDTO(
                equipment.getName(),
                equipment.getType(),
                equipment.getBonusType(),
                equipment.getBonusType_reason(),
                equipment.getBonusValue(),
                equipment.getEffects(),
                equipment.getImageUrl()
        );
    }
}
