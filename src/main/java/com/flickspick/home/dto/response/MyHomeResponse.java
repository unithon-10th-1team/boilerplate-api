package com.flickspick.home.dto.response;

import com.flickspick.movie.model.MovieModel;
import com.flickspick.ott.model.OttModel;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyHomeResponse {
    private UserModel user;
    private List<RecTypeModel> recType;
    private List<String> tags;
    private List<MovieModel> similarMovies;
    private List<MovieModel> differentMovies;

    public static MyHomeResponse toResponse(UserModel user, List<RecTypeModel> recType, List<String> tags, List<MovieModel> similarMovies, List<MovieModel> differentMovies) {
        return new MyHomeResponse(user, recType, tags, similarMovies, differentMovies);
    }
}
