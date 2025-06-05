package cc.team3.character.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Effect {
    private String type;
    private Double chance;
    private Integer duration;
    private Integer damageForTurn;
}
