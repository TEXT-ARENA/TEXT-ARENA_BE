package cc.team3.character.domain;

import cc.team3.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long characterId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "character_name", length = 12, nullable = false)
    private String characterName;

    @Column(name = "description", length = 30, nullable = false)
    private String description;

    @Column(name = "exp")
    @Builder.Default
    private Integer exp = 0;

    @Column(name = "level")
    @Builder.Default
    private Integer level = 1;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

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
}
