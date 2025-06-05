package cc.team3.character.domain;

import cc.team3.character.domain.enums.EquipmentType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "equipments")
@Getter
@Builder
@AllArgsConstructor
public class Equipment {
    @Id
    private String id;
    private String name;
    private EquipmentType type; // WEAPON, TOP, BOTTOM, SHOES
    private String bonusType; // "attackBonus", "defenseBonus", "speedBonus" 등
    private Object bonusValue; // 보너스 값 (int, double 등)
    private List<Effect> effects;
}