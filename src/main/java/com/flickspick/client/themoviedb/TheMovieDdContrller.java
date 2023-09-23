package com.flickspick.client.themoviedb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class TheMovieDdContrller {
    private final ThemoviedbClient themoviedbClient;

    @PostMapping("/batch")
    public void batch() {
        var a = themoviedbClient.getTrendMovies(1);
        System.out.println(a);
    }
}
