package cc.team3.character.service;

import cc.team3.character.converter.CharacterConverter;
import cc.team3.character.domain.Character;
import cc.team3.character.domain.Equipment;
import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.repository.CharacterRepository;
import cc.team3.global.feignclient.AiServerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final EquipmentService equipmentService;
    private final AiServerClient aiServerClient;

    public CharacterResponse.CharacterDetailsResponseDTO createCharacter(CharacterRequest.CharacterCreateRequestDTO request) {
        // AI 서버로부터 API 요청
        CharacterResponse.CharacterCreateResponseDTO characterDTO = aiServerClient.createCharacter(request);

        String equipmentId = equipmentService.createEquipment(characterDTO.equipmentDTOList().getFirst());

        Character character = CharacterConverter.toCharacter(characterDTO, equipmentId);
        characterRepository.save(character);

        // 리턴 값 추출.
        return readCharacter(character);
    }

    public CharacterResponse.CharacterDetailsResponseDTO readCharacter(Character character) {
        List<String> equipmentIds = character.getEquipmentIds();

        List<Equipment> equipments = equipmentService.findEquipments(equipmentIds);
        return CharacterConverter.toCharacterDetailsResponseDTO(character, equipments);
    }
}
