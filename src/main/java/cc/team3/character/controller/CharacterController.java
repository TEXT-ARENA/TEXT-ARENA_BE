package cc.team3.character.controller;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.service.CharacterService;
import cc.team3.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping("/{userId}")
    public ApiResponse<CharacterResponse.CharacterDetailsResponseDTO> createCharacter(@PathVariable("userId") Long userId, @RequestBody CharacterRequest.CharacterCreateRequestDTO request) {
        return ApiResponse.onSuccess(characterService.createCharacter(userId, request));
    }

    @Operation(summary = "장비 생성", description = "장비를 생성하고자 하는 캐릭터  ID를 넣어주세요 ! <br><br>" +
        "equipmentType: 무기, 모자, 상의, 신발 중 하나 입력해주세요 ! <br> equipmentName: 장비 이름 <br> description: 장비 설명")
    @PostMapping("/{characterId}/equipments")
    public ApiResponse<CharacterResponse.CharacterDetailsResponseDTO> createWeapon(@PathVariable("characterId") Long characterId, @RequestBody CharacterRequest.CreateEquipmentRequestDTO request) {
        return ApiResponse.onSuccess(characterService.createEquipment(characterId, request));
    }
}
