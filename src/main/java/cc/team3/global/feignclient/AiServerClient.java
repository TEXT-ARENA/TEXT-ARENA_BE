package cc.team3.global.feignclient;

import cc.team3.character.dto.CharacterRequest;
import cc.team3.character.dto.CharacterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aiServerClient", url = "http://1.2.3.4:8080")
public interface AiServerClient {

    @PostMapping("/api/characters")
    CharacterResponse.CharacterCreateResultDTO createCharacter(@RequestBody CharacterRequest.CharacterCreateRequestDTO request);
}
