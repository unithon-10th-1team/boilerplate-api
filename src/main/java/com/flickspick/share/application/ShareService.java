package com.flickspick.share.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.application.RecommendTypeService;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.share.dto.response.ShareResponse;
import com.flickspick.user.application.UserService;
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final UserService userService;
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;

    @SneakyThrows
    public ShareResponse share(AuthUser authUser) {
        var userCf = userService.asyncGetUserModel(authUser.getId());
        var userMovieHistoryCf = userMovieHistoryService.asyncGetRecentHistory(authUser.getId());

        CompletableFuture.allOf(userCf, userMovieHistoryCf).join();

        var userMovieHistory = userMovieHistoryCf.get();

        RecTypeModel recTypeModel = recommendTypeService.getRecTypeModel(userMovieHistory.getRecommendTypeId());

        List<MovieModel> similarMovies = movieService.getList(userMovieHistory.getMovieId(), 2);
        MovieModel movieModel = movieService.get(userMovieHistory.getMovieId());
        similarMovies.add(movieModel);

        return new ShareResponse(
                userCf.get(),
                List.of(recTypeModel),
                recTypeModel.getTags(),
                similarMovies
        );
    }
}
