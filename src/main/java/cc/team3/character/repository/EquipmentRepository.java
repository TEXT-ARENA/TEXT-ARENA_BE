package cc.team3.character.repository;

import cc.team3.character.domain.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipmentRepository extends MongoRepository<Equipment, String> {
}
