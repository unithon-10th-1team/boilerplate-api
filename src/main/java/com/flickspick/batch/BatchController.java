package com.flickspick.batch;

import com.flickspick.client.chatgpt.ChatGPTClient;
import com.flickspick.client.themoviedb.ThemoviedbClient;
import com.flickspick.movie.domain.Movie;
import com.flickspick.movie.infrastructure.MovieRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "배치 서비스")
@RestController
@RequestMapping(path = "/api/v1/batch", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;
    private final ChatGPTClient chatGPTClient;
    private final ThemoviedbClient themoviedbClient;
    private final MovieRepository movieRepository;

    @PostMapping
    public void batch() {
        batchService.batch();
    }

    @PostMapping("/test")
    public void testBatch() {
        var movie =
                themoviedbClient.getTrendMovies(4).getResults().stream()
                        .map(
                                m ->
                                        Movie.builder()
                                                .title(m.getTitle())
                                                .plot(m.getOverview())
                                                .imageUrl(
                                                        "https://image.tmdb.org/t/p/w780/"
                                                                + m.getPoster_path())
                                                .build())
                        .collect(Collectors.toList());

        movieRepository.saveAll(movie);
    }
}
