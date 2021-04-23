package org.nicktorwald.tlotr.character;

import org.nicktorwald.tlotr.character.domain.Character;
import org.nicktorwald.tlotr.character.domain.Race;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
class DefaultCharacterService implements CharacterService {

    private final  CharacterRepository characterRepository;

    DefaultCharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public Flux<Character> getAllCharacters() {
        return characterRepository.findAll()
                .map(this::toCharacterFromEntity);
    }

    private Character toCharacterFromEntity(CharacterEntity entity) {
        return Character.of(entity.getName(), Race.valueOf(entity.getRace()));
    }

}