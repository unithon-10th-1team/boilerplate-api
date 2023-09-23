package com.flickspick.movie.model;

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
}
