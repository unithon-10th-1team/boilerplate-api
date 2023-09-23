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
public class HomeResponse {
    private UserModel user;
    private List<OttModel> otts;
    private List<RecTypeModel> recType;
    private List<String> tags;
    private List<MovieModel> similarMovies;
    private List<MovieModel> differentMovies;
}
