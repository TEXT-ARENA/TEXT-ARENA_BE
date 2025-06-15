package cc.team3.character.controller;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.service.CharacterService;
import cc.team3.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

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

    @Operation(summary = "전투 승패 기록", description = "승자와 패자 캐릭터 ID를 넣어주세요 !")
    @PostMapping("/battle")
    public ApiResponse<CharacterResponse.RecordBattleResponseDTO> recordBattle(@RequestBody CharacterRequest.RecordBattleRequestDTO request) {
        return ApiResponse.onSuccess(characterService.recordBattle(request));
    }

    @Operation(summary = "캐릭터 목록 조회", description = "캐릭터 목록을 조회하고자 하는 유저의 ID를 넣어주세요 !")
    @GetMapping("/list/{userId}")
    public ApiResponse<List<CharacterResponse.ReadCharacterListDTO>> getCharacterList(@PathVariable("userId") Long userId) {
        return ApiResponse.onSuccess(characterService.readCharacterList(userId));
    }

    @Operation(summary = "내 캐릭터 및 상대 캐릭터 상세 조회", description = "전투를 희망하고자 하는 캐릭터 ID를 넣어주세요 !")
    @GetMapping("/battle/{characterId}")
    public ApiResponse<List<CharacterResponse.CharacterDetailsResponseDTO>> readCharactersForBattle(@PathVariable("characterId") Long characterId) {
        return ApiResponse.onSuccess(characterService.readCharactersForBattle(characterId));
    }

    @Operation(summary = "캐릭터 삭제", description = "삭제하고자 하는 캐릭터 ID를 넣어주세요 !")
    @DeleteMapping("/{characterId}")
    public ApiResponse<CharacterResponse.DeleteCharacterResultDTO> deleteCharacter(@PathVariable("characterId") Long characterId) {
        return ApiResponse.onSuccess(characterService.deleteCharacter(characterId));
    }
}


