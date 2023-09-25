package com.flickspick.rec.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.movie_recommendtype.MovieRecommendTypeNotFoundException;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie_recommendtype.infrastructure.MovieRecommendTypeRepository;
import com.flickspick.rec.dto.RecRequest;
import com.flickspick.rec.dto.RecRequest.QuestionModel;
import com.flickspick.rec.dto.RecResponse;
import com.flickspick.recommendtype.application.RecommendTypeService;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.application.UserService;
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecService {
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;
    private final MovieRecommendTypeRepository movieRecommendTypeRepository;
    private final UserService userService;

    @SneakyThrows
    public RecResponse get(AuthUser user, RecRequest request) {
        var userModel = userService.getUserModel(user.getId());

        Map<Long, Long> questionAndAnswer =
                request.getAnswers().stream()
                        .collect(
                                Collectors.toMap(
                                        QuestionModel::getAnswerId,
                                        QuestionModel::getQuestionId,
                                        (a, b) -> b));

        Long movieId = (long) (new Random().nextInt(65 - 30 + 1) + 30);

        Long recommendTypeId =
                movieRecommendTypeRepository
                        .findByMovieId(movieId)
                        .orElseThrow(
                                () ->
                                        new MovieRecommendTypeNotFoundException(
                                                ErrorType.MOVIE_RECOMMEND_TYPE_NOT_FOUND_ERROR))
                        .getRecommendTypeId();

        UserMovieHistory userMovieHistory =
                UserMovieHistory.of(user.getId(), recommendTypeId, movieId);
        userMovieHistory.updateQuestionAndAnswer(questionAndAnswer);

        RecTypeModel recTypeModel = recommendTypeService.get(recommendTypeId);

        var movieModel = movieService.get(movieId);
        var recMovies = movieService.getList(movieId, 3);

        userMovieHistoryService.saveUserMovieHistory(userMovieHistory);

        return RecResponse.toResponse(recTypeModel, movieModel, recMovies, userModel);
    }
}
