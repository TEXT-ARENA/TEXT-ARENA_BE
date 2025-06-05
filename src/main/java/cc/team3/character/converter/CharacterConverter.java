package cc.team3.character.converter;

import cc.team3.character.domain.Character;
import cc.team3.character.domain.Equipment;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.user.domain.User;

import java.util.List;

public class CharacterConverter {
    public static CharacterResponse.CharacterDetailsResponseDTO toCharacterDetailsResponseDTO(Character character, List<Equipment> equipmentList) {
        return new CharacterResponse.CharacterDetailsResponseDTO(
                character.getCharacterName(),
                character.getHp(),
                character.getHp_reason(),
                character.getAttack(),
                character.getDefense(),
                character.getCriticalChance(),
                character.getCriticalDamage(),
                character.getSpeed(),
                character.getDodgeChance(),
                equipmentList,
                character.getStatusEffects()
        );
    }

    public static Character toCharacter(User user, CharacterResponse.CharacterCreateResponseDTO request, String equipmentId) {
        List<String> equipmentList = List.of(equipmentId);
        return Character.builder()
                .user(user)
                .characterName(request.name())
                .hp(request.hp())
                .hp_reason(request.hpReason())
                .attack(request.attack())
                .defense(request.defense())
                .criticalChance(request.criticalChance())
                .criticalDamage(request.criticalDamage())
                .speed(request.speed())
                .dodgeChance(request.dodgeChance())
                .accuracy(request.accuracy())
                .equipmentIds(equipmentList)
                .statusEffects(request.statusEffect())
                .build();
    }
}
