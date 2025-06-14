package cc.team3.character.repository;

import cc.team3.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUser_userId(Long userId);
}
