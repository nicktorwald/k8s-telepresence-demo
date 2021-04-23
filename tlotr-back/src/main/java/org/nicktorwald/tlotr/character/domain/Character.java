package org.nicktorwald.tlotr.character.domain;

import java.util.Objects;

/**
 * Defines a typical character from
 * Middle-earth.
 */
public class Character {
    private final String name;
    private final Race race;

    private Character(String name, Race race) {
        this.name = name;
        this.race = race;
    }

    public static Character of(String name, Race race) {
        return new Character(
                Objects.requireNonNull(name),
                Objects.requireNonNull(race)
        );
    }

    public String getName() {
        return name;
    }

    public Race getRace() {
        return race;
    }
}
