package com.flickspick.share.dto.response;

import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShareResponse {
    private UserModel user;
    private List<RecTypeModel> recType;
    private List<String> tags;
    private List<MovieModel> similarMovies;
}
