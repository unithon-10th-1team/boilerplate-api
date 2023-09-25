package com.flickspick.client.themoviedb;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheMovieDBTrendModel {
    private Integer page;
    private List<TheMovieDbListModel.MovieItem> results;
}
