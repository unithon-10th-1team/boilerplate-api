package com.flickspick.home.dto.response;

import com.flickspick.movie.model.MovieModel;
import com.flickspick.ott.model.OttModel;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.model.UserModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyProfileResponse {
    private UserModel user;
    private List<OttModel> otts;
    private List<RecTypeModel> recType;
    private List<String> tags;
    private List<MovieModel> similarMovies;

    public static MyProfileResponse toResponse(
            UserModel user,
            List<OttModel> otts,
            List<RecTypeModel> recType,
            List<String> tags,
            List<MovieModel> similarMovies) {
        return new MyProfileResponse(user, otts, recType, tags, similarMovies);
    }
}
