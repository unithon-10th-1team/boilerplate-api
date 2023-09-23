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
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecService {
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;
    private final MovieRecommendTypeRepository movieRecommendTypeRepository;

    @SneakyThrows
    public RecResponse get(AuthUser user, RecRequest request) {
        Random rand = new SecureRandom();
        Map<Long, Long> questionAndAnswer = request.getAnswers()
                .stream()
                .collect(
                        Collectors.toMap(
                                QuestionModel::getAnswerId,
                                QuestionModel::getQuestionId,
                                (a, b) -> b)
                );

        Long movieId = (Math.abs(rand.nextLong()) % movieService.getMovieCount()) + 1;
        Long recommendTypeId = movieRecommendTypeRepository.findByMovieId(movieId)
                .orElseThrow(() -> new MovieRecommendTypeNotFoundException(ErrorType.MOVIE_RECOMMEND_TYPE_NOT_FOUND_ERROR))
                .getRecommendTypeId();

        UserMovieHistory userMovieHistory = UserMovieHistory.of(user.getId(), recommendTypeId, movieId);
        userMovieHistory.updateQuestionAndAnswer(questionAndAnswer);

        RecTypeModel recTypeModel = recommendTypeService.get(recommendTypeId);

        var movieModelCf = movieService.asyncGetMovieModel(movieId);
        var recMoviesCf = movieService.asyncGetMovieModelList(movieId, 3);

        CompletableFuture.allOf(movieModelCf, recMoviesCf).join();

        userMovieHistoryService.saveUserMovieHistory(userMovieHistory);

        return RecResponse.toResponse(recTypeModel, movieModelCf.get(), recMoviesCf.get());
    }
}
