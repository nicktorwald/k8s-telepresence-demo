package org.nicktorwald.tlotr.character.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 *
 */
@Configuration
class CharacterRoutingConfig {

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(CharacterHandler characterHandler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/characters")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                characterHandler::getCharacters
        );
    }

}
