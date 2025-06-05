package cc.team3.global.feignclient;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aiServerClient", url = "http://3.236.223.83:8000")
public interface AiServerClient {

    @PostMapping("/api/characters")
    CharacterResponse.CharacterCreateResponseDTO createCharacter(@RequestBody CharacterRequest.CharacterCreateRequestDTO request);

    @PostMapping("/api/equipments")
    CharacterResponse.EquipmentDTO createEquipment(@RequestBody CharacterRequest.CreateEquipmentRequestDTO request);
}
