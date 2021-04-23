package org.nicktorwald.tlotr.character;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Flux;

interface CharacterRepository extends Repository<CharacterEntity, Long> {

    Flux<CharacterEntity> findAll();

}
