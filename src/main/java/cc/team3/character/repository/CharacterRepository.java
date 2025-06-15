package cc.team3.character.repository;

import cc.team3.character.domain.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUser_userId(Long userId);

    long countByUser_userIdNot(Long userId);

    Page<Character> findByUser_userIdNot(Long userId, Pageable pageable);

    @Query(
            value = "SELECT equipment_ids FROM equipments WHERE character_character_id=:characterId",
            nativeQuery = true
    )
    List<String> findEquipments(Long characterId);
}
