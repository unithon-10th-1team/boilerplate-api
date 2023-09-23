package com.flickspick.client.themoviedb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheMovieDBTrendModel {
    private Integer page;
    private List<TheMovieDbListModel.MovieItem> results;
}
