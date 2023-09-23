package com.flickspick.client.themoviedb;

import com.flickspick.common.client.WebClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ThemoviedbClient {
    @Value("${themoviedb.api-key}")
    private String apiKey;

    private final WebClient webClient = WebClientFactory
            .generate("https://api.themoviedb.org", 20000, 15000L, 15000L);

    public TheMovieDbListModel getMovies(int list) {
        return webClient.get()
                .uri("/3/list/" + list + "?language=ko")
                .header("Authorization", "Bearer " + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TheMovieDbListModel.class).block();
    }
}
