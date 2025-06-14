package cc.team3.character.converter;

import cc.team3.character.domain.Equipment;
import cc.team3.character.domain.enums.EquipmentType;
import cc.team3.character.dto.CharacterResponse;

public class EquipmentConverter {
    public static Equipment toEquipment(CharacterResponse.EquipmentDTO request, String name, String type) {
        EquipmentType equipmentType = EquipmentType.fromDescription(type);
        return Equipment.builder()
                .name(name)
                .type(equipmentType)
                .bonusType(request.bonusType())
                .bonusValue(request.bonusValue())
                .effects(request.effects())
                .build();
    }
}
