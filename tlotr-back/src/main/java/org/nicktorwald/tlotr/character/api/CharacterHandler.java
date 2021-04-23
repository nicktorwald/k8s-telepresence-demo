package org.nicktorwald.tlotr.character.api;

import org.nicktorwald.tlotr.character.domain.Character;
import org.nicktorwald.tlotr.character.CharacterService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 *
 */
@Component
class CharacterHandler {

    private final CharacterService characterService;

    CharacterHandler(CharacterService characterService) {
        this.characterService = characterService;
    }

    public Mono<ServerResponse> getCharacters(ServerRequest serverRequest) {
        var allCharacters = characterService.getAllCharacters();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(allCharacters, Character.class);
    }

}
