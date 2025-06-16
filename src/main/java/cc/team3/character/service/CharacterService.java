package cc.team3.character.service;

import cc.team3.character.converter.CharacterConverter;
import cc.team3.character.domain.Character;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;
    private final EquipmentService equipmentService;
    private final AiServerClient aiServerClient;

    @Transactional
    public CharacterResponse.CharacterDetailsResponseDTO createCharacter(Long userId, CharacterRequest.CharacterCreateRequestDTO request) {
        // AI 서버로부터 API 요청
        CharacterResponse.CharacterCreateResponseDTO characterDTO = aiServerClient.createCharacter(request).getResult();
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Character character = CharacterConverter.toCharacter(user, characterDTO, request.characterName());
        characterRepository.save(character);

        // 리턴 값 추출.
        return readCharacter(character);
    }

    @Transactional
    public CharacterResponse.CharacterDetailsResponseDTO createEquipment(Long characterId, CharacterRequest.CreateEquipmentRequestDTO request) {
        // 중복 체크
        validateDuplicateEquipmentType(characterId, request.equipmentType());

        // AI 서버로부터 API 요청
        CharacterResponse.EquipmentDTO equipmentDTO = aiServerClient.createEquipment(request).getResult();
        String equipmentId = equipmentService.createEquipment(equipmentDTO, request.equipmentName(), request.equipmentType());

        Character character = characterRepository.findById(characterId).orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));
        character.getEquipmentIds().add(equipmentId);

        return readCharacter(character);
    }

    private void validateDuplicateEquipmentType(Long characterId, String equipmentType) {
        List<String> equipmentIds = characterRepository.findEquipments(characterId);
        List<Equipment> equipments = equipmentService.findEquipments(equipmentIds);

        boolean isTypeExists = equipments.stream()
                .anyMatch(equipment -> equipment.getType().getDescription().equals(equipmentType));
        if (isTypeExists) {
            throw new GeneralException(ErrorStatus.EQUIPMENT_ALREADY_EXIST);
        }
    }

    public List<CharacterResponse.ReadEquipmentsResultDTO> readEquipments(Long characterId) {
        List<String> equipmentIds = characterRepository.findEquipments(characterId);
        List<Equipment> equipments = equipmentService.findEquipments(equipmentIds);

        return equipments.stream().map(e -> {
            return CharacterConverter.toReadEquipmentsResultDTO(e);
        }).collect(Collectors.toList());
    }

    public CharacterResponse.CharacterDetailsResponseDTO readCharacter(Character character) {
        List<String> equipmentIds = character.getEquipmentIds();
        List<Equipment> equipments = (equipmentIds.isEmpty()) ? new ArrayList<>() : equipmentService.findEquipments(equipmentIds);

        return CharacterConverter.toCharacterDetailsResponseDTO(character, equipments);
    }

    @Transactional
    public CharacterResponse.RecordBattleResponseDTO recordBattle(CharacterRequest.RecordBattleRequestDTO request) {
        Character winner = characterRepository.findById(request.winnerId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));
        Character loser = characterRepository.findById(request.loserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));

        winner.incrementWins();
        winner.addExp(100);

        loser.incrementLosses();
        loser.addExp(50);

        return new CharacterResponse.RecordBattleResponseDTO(winner.getCharacterId(), loser.getCharacterId());
    }

    public List<CharacterResponse.ReadCharacterListDTO> readCharacterList(Long userId) {
        List<Character> characterList = characterRepository.findByUser_userId(userId);
        return characterList.stream().map(c -> {
            return CharacterConverter.toReadCharacterListDTO(c);
        }).collect(Collectors.toList());
    }

    public List<CharacterResponse.CharacterDetailsResponseDTO> readCharactersForBattle(Long characterId) {
        Character myCharacter = characterRepository.findById(characterId).orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));

        Long myCharacterId = myCharacter.getCharacterId();

        // 나를 제외한 상대방 캐릭터 수 카운트
        long opponentCount = characterRepository.countByUser_userIdNot(myCharacterId);

        if (opponentCount == 0) {
            throw new GeneralException(ErrorStatus.EMPTY_BATTLE_OPPONENT);
        }

        // 랜덤 인덱스 생성 후 상대방 캐릭터 1명 조회
        int randomIndex = (int) (Math.random() * opponentCount);
        Page<Character> opponentPage = characterRepository.findByUser_userIdNot(
                myCharacterId,
                PageRequest.of(randomIndex, 1) // 랜덤 위치의 데이터 1개만 가져오기
        );

        Character opponentCharacter = opponentPage.getContent().get(0);

        return List.of(myCharacter, opponentCharacter).stream()
                .map(c -> {
                    return readCharacter(c);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public CharacterResponse.DeleteCharacterResultDTO deleteCharacter(Long characterId) {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new GeneralException(ErrorStatus.CHARACTER_NOT_FOUND));
        characterRepository.delete(character);

        return CharacterConverter.toDeleteCharacterResultDTO(characterId);
    }
}
