package cc.team3.character.domain.enums;

import cc.team3.global.apiPayload.exception.GeneralException;
import cc.team3.global.apiPayload.status.ErrorStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EquipmentType {
    weapon("무기"),
    hat("모자"),
    top("상의"),
    shoes("신발");

    private final String description;

    public static EquipmentType fromDescription(String description) {
        for (EquipmentType equipmentType : EquipmentType.values()) {
            if (equipmentType.description.equals(description)) {
                return equipmentType;
            }
        }
        throw new GeneralException(ErrorStatus.EQUIPMENT_TYPE_NOT_FOUND);
    }
}
