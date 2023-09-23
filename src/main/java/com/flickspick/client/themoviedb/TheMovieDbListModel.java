package com.flickspick.client.themoviedb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheMovieDbListModel {
    private String created_by;
    private String description;
    private Integer favorite_count;
    private Integer id;
    private List<MovieItem> items;
    private Integer item_count;
    private String iso_639_1;
    private String name;
    private String poster_path;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MovieItem {
        private boolean adult;
        private String backdrop_path;
        private List<Integer> genre_ids;
        private Long id;
        private String media_type;
        private String original_language;
        private String original_title;
        private String overview;
        private Double popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private boolean video;
        private Integer vote_average;
        private Long vote_count;
    }
}
