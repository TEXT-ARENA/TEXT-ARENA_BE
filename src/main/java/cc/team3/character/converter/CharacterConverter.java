package cc.team3.character.converter;

import cc.team3.character.domain.Character;
import cc.team3.character.domain.Equipment;
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
                character.getAttack(),
                character.getDefense(),
                character.getCriticalChance(),
                character.getCriticalDamage(),
                character.getSpeed(),
                character.getDodgeChance(),
                character.getAccuracy(),
                equipmentList,
                character.getStatusEffects()
        );
    }

    public static Character toCharacter(User user, CharacterResponse.CharacterCreateResponseDTO request, String characterName) {
        return Character.builder()
                .user(user)
                .characterName(characterName)
                .hp(request.hp())
                .hp_reason(request.hp_reason())
                .attack(request.attack())
                .defense(request.defense())
                .criticalChance(request.criticalChance())
                .criticalDamage(request.criticalDamage())
                .speed(request.speed())
                .dodgeChance(request.dodgeChance())
                .accuracy(request.accuracy())
                .statusEffects(new ArrayList<>())
                .build();
    }

    public static CharacterResponse.ReadCharacterListDTO toReadCharacterListDTO(Character character) {
        return new CharacterResponse.ReadCharacterListDTO(
                character.getCharacterId(),
                character.getCharacterName(),
                character.getHp(),
                character.getAttack(),
                character.getDefense());
    }

    public static CharacterResponse.DeleteCharacterResultDTO toDeleteCharacterResultDTO(Long characterId) {
        return new CharacterResponse.DeleteCharacterResultDTO(
                characterId,
                LocalDateTime.now()
        );
    }
}
