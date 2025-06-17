package cc.team3.character.service;

import cc.team3.character.converter.EquipmentConverter;
import cc.team3.character.domain.Equipment;
import cc.team3.character.domain.enums.EquipmentType;
import cc.team3.character.dto.CharacterResponse;
import cc.team3.character.repository.EquipmentRepository;
import cc.team3.global.apiPayload.exception.GeneralException;
import cc.team3.global.apiPayload.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Transactional
    public String createEquipment(CharacterResponse.EquipmentDTO equipmentDTO, String equipmentName, String equipmentType) {
        Equipment equipment = EquipmentConverter.toEquipment(equipmentDTO, equipmentName, equipmentType);
        equipmentRepository.save(equipment);

        return equipment.getId();
    }

    public List<Equipment> findEquipments (List<String> equipmentIds) {
        return equipmentRepository.findAllById(equipmentIds);
    }

    boolean existsByIdInAndTypeDescription(List<String> equipmentIds, String equipmentType) {
        return equipmentRepository.existsByIdInAndTypeDescription(equipmentIds, EquipmentType.fromDescription(equipmentType));
    }

    long deleteByEquipmentIds(List<String> equipmentIds) {
        return equipmentRepository.deleteByIdIn(equipmentIds);
    }
}
