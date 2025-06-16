package cc.team3.character.repository;

import cc.team3.character.domain.Equipment;
import cc.team3.character.domain.enums.EquipmentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {
    @Query(value = "{ '_id': { '$in': ?0 }, 'type': ?1 }", exists = true)
    boolean existsByIdInAndTypeDescription(List<String> equipmentIds, EquipmentType equipmentType);
}
