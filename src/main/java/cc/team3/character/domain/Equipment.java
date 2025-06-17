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
    private String type;
    private String bonusType; // "attackBonus", "defenseBonus", "speedBonus" 등
    private Double bonusValue;
    private List<Effect> effects;
    private String image;
}