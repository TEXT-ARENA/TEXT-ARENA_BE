package cc.team3.character.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Effect {
    private String type;
    private String typeReason;
    private Double chance;
    private Integer duration;
    private Integer bonusIncreasePerTurn;
}
