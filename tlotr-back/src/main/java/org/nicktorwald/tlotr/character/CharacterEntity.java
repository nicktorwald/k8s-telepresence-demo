package org.nicktorwald.tlotr.character;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Defines a persistent character view.
 */
@Table("character")
class CharacterEntity {

    @Id
    private Long characterId;
    private String name;
    private String race;

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(final Long characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(final String race) {
        this.race = race;
    }

}
