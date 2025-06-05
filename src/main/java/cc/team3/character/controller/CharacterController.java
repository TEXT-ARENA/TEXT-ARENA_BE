package cc.team3.character.controller;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.service.CharacterService;
import cc.team3.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping("/{userId}")
    public ApiResponse<CharacterResponse.CharacterDetailsResponseDTO> createCharacter(@RequestParam("userId") Long userId, @RequestBody CharacterRequest.CharacterCreateRequestDTO request) {
        return ApiResponse.onSuccess(characterService.createCharacter(userId, request));
    }

    @PostMapping("/{characterId}/weapons")
    public ApiResponse<CharacterResponse.CharacterDetailsResponseDTO> createWeapon(@RequestParam("characterId") Long characterId, @RequestBody CharacterRequest.CreateEquipmentRequestDTO request) {
        return ApiResponse.onSuccess(characterService.createEquipment(characterId, request));
    }
}
