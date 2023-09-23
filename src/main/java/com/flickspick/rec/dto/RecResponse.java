package com.flickspick.rec.dto;

import com.flickspick.movie.model.MovieModel;
import com.flickspick.rec.model.RecTypeModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecResponse {
    private RecTypeModel recType;
    private MovieModel movie;
    private List<MovieModel> recMovies;
}
