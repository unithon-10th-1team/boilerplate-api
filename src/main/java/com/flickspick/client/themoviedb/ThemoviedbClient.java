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

    private final WebClient webClient =
            WebClientFactory.generate("https://api.themoviedb.org", 20000, 15000L, 15000L);

    public TheMovieDbListModel getMovies(int list) {
        return webClient
                .get()
                .uri("/3/list/" + list + "?language=ko")
                .header("Authorization", "Bearer " + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TheMovieDbListModel.class)
                .block();
    }

    public TheMovieDBTrendModel getTrendMovies(int list) {
        return webClient
                .get()
                .uri(
                        "/3/discover/movie?include_adult=false&include_video=false&language=ko&page=1&sort_by=popularity.desc&with_genres=10749%7C18%7C10751%7C99%7C36%7C27")
                .header("Authorization", "Bearer " + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TheMovieDBTrendModel.class)
                .block();
    }
}
