package cc.team3.global.feignclient;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.global.apiPayload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aiServerClient", url = "http://35.153.144.33:8000")
public interface AiServerClient {

    @PostMapping("/api/characters")
    ApiResponse<CharacterResponse.CharacterCreateResponseDTO> createCharacter(@RequestBody CharacterRequest.CharacterCreateRequestDTO request);

    @PostMapping("/api/equipments")
    CharacterResponse.EquipmentDTO createEquipment(@RequestBody CharacterRequest.CreateEquipmentRequestDTO request);
}
