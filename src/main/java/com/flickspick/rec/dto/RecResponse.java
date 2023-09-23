package com.flickspick.rec.dto;

import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecResponse {
    private UserModel user;
    private RecTypeModel recType;
    private MovieModel movie;
    private List<MovieModel> recMovies;

    public static RecResponse toResponse(
            RecTypeModel recTypeModel,
            MovieModel movie,
            List<MovieModel> recMovies,
            UserModel user
    ) {
        return new RecResponse(user, recTypeModel, movie, recMovies);
    }
}
