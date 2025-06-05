package cc.team3.character.service;

import cc.team3.character.converter.CharacterConverter;
import cc.team3.character.domain.Character;
import cc.team3.character.domain.Effect;
import cc.team3.character.domain.Equipment;
import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.repository.CharacterRepository;
import cc.team3.global.apiPayload.exception.GeneralException;
import cc.team3.global.apiPayload.status.ErrorStatus;
import cc.team3.global.feignclient.AiServerClient;
import cc.team3.user.domain.User;
import cc.team3.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final EquipmentService equipmentService;
    private final AiServerClient aiServerClient;

    @Transactional
    public CharacterResponse.CharacterDetailsResponseDTO createCharacter(Long userId, CharacterRequest.CharacterCreateRequestDTO request) {
        // 임시 테스트용
        CharacterResponse.CharacterCreateResponseDTO characterDTO = forTest();

//        // AI 서버로부터 API 요청
//        CharacterResponse.CharacterCreateResponseDTO characterDTO = aiServerClient.createCharacter(request);
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Character character = CharacterConverter.toCharacter(user, characterDTO);
        characterRepository.save(character);

        // 리턴 값 추출.
        return readCharacter(character);
    }

    private CharacterResponse.CharacterCreateResponseDTO forTest() {
        return new CharacterResponse.CharacterCreateResponseDTO(
                "엘라", 100, "엘라니까", 12, 5, 0.10, 1.5, 70, 0.05, 0.92, new ArrayList<>()
        );
    }

    @Transactional
    public CharacterResponse.CharacterDetailsResponseDTO createEquipment(Long characterId, CharacterRequest.CreateEquipmentRequestDTO request) {
        // 임시 테스트용
        List<Effect> effects = List.of(new Effect("poison", 0.25, 3, 5));
        CharacterResponse.EquipmentDTO equipmentDTO = new CharacterResponse.EquipmentDTO(
                "Posioned Dagger", "attackBonus", 3, effects
        );

//        // AI 서버로부터 API 요청
//        CharacterResponse.EquipmentDTO equipmentDTO = aiServerClient.createEquipment(request);
        String equipmentId = equipmentService.createEquipment(equipmentDTO, request.equipmentType());

        Character character = characterRepository.findById(characterId).orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));
        character.getEquipmentIds().add(equipmentId);

        return readCharacter(character);
    }

    public CharacterResponse.CharacterDetailsResponseDTO readCharacter(Character character) {
        List<String> equipmentIds = character.getEquipmentIds();
        List<Equipment> equipments = (equipmentIds.isEmpty()) ? new ArrayList<>() : equipmentService.findEquipments(equipmentIds);

        return CharacterConverter.toCharacterDetailsResponseDTO(character, equipments);
    }
}
