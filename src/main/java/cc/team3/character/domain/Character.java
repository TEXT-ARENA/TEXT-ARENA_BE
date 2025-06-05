package cc.team3.character.domain;

import cc.team3.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long characterId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "character_name", length = 50, nullable = false)
    private String characterName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    @Column(name = "exp")
    private Integer exp;

    @Column(name = "level")
    private Integer level;

    @Column(name = "critical_chance")
    private Double criticalChance;

    @Column(name = "critical_damage")
    private Double criticalDamage;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "dodge_chance")
    private Double dodgeChance;

    @Column(name = "accuracy")
    private Double accuracy;

    @ElementCollection
    @CollectionTable(name = "equipments")
    private List<String> equipmentIds; // MongoDB의 장비 ID들

    @ElementCollection
    @CollectionTable(name = "status_effects")
    private List<String> statusEffects;
}
