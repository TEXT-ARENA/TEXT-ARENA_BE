package cc.team3.global.feignclient;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.global.apiPayload.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aiServerClient", url = "https://62u2ykms2i.execute-api.us-east-1.amazonaws.com/inha-pj-03")
public interface AiServerClient {

    @PostMapping("/api/characters")
    ApiResponse<CharacterResponse.CharacterCreateResponseDTO> createCharacter(@RequestBody CharacterRequest.CharacterCreateRequestDTO request);

    @PostMapping("/api/equipments")
    ApiResponse<CharacterResponse.EquipmentDTO> createEquipment(@RequestBody CharacterRequest.CreateEquipmentRequestDTO request);
}
