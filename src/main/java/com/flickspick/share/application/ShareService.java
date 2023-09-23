package com.flickspick.share.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.application.RecommendTypeService;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.share.dto.response.ShareResponse;
import com.flickspick.user.application.UserService;
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final UserService userService;
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;

    public ShareResponse share(AuthUser authUser) {
        var user = userService.getUserModel(authUser.getId());
        UserMovieHistory userMovieHistory = userMovieHistoryService.getRecentHistory(user.getId());
        RecTypeModel recTypeModel = recommendTypeService.getRecTypeModel(userMovieHistory.getRecommendTypeId());

        List<MovieModel> similarMovies = movieService.getMovieModelList(userMovieHistory.getMovieId(), 2);
        MovieModel movieModel = movieService.getMovieModel(userMovieHistory.getMovieId());
        similarMovies.add(movieModel);

        return new ShareResponse(
                user,
                List.of(recTypeModel),
                recTypeModel.getTags(),
                similarMovies
        );
    }
}
