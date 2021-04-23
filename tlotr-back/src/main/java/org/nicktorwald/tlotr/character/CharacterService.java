package org.nicktorwald.tlotr.character;

import org.nicktorwald.tlotr.character.domain.Character;
import reactor.core.publisher.Flux;

/**
 *
 */
public interface CharacterService {

    /**
     * Loads all known characters.
     *
     * @return found characters
     */
    Flux<Character> getAllCharacters();

}
