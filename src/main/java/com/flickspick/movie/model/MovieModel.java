package com.flickspick.movie.model;

import com.flickspick.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieModel {
    private Long id;
    private String title;
    private String plot;
    private String reason;
    private String people;
    private String director;
    private String producer;
    private String scenario;
    private Integer grade;
    private String imageUrl;

    public static MovieModel toModel(Movie movie) {
        return new MovieModel(
                movie.getId(),
                movie.getTitle(),
                movie.getPlot(),
                movie.getReason(),
                movie.getPeople(),
                movie.getDirector(),
                movie.getProducer(),
                movie.getScenario(),
                movie.getGrade(),
                movie.getImageUrl());
    }
}
