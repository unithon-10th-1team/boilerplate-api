package com.flickspick.rec.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.movie_recommendtype.MovieRecommendTypeNotFoundException;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.movie_recommendtype.domain.MovieRecommendType;
import com.flickspick.movie_recommendtype.infrastructure.MovieRecommendTypeRepository;
import com.flickspick.rec.dto.RecRequest;
import com.flickspick.rec.dto.RecRequest.QuestionModel;
import com.flickspick.rec.dto.RecResponse;
import com.flickspick.recommendtype.application.RecommendTypeService;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import com.flickspick.user_movie_history.domain.UserMovieHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecService {
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;
    private final MovieRecommendTypeRepository movieRecommendTypeRepository;

    public RecResponse get(AuthUser user, RecRequest request) {
        Random rand = new SecureRandom();
        Map<Long, Long> questionAndAnswer = new HashMap<>();
        for(QuestionModel questionModel : request.getAnswers()) {
            questionAndAnswer.put(questionModel.getAnswerId(), questionModel.getQuestionId());
        }

        Long movieId = (Math.abs(rand.nextLong()) % movieService.getMovieCount()) + 1;
        Long recommendTypeId = movieRecommendTypeRepository.findByMovieId(movieId)
                .orElseThrow(() -> new MovieRecommendTypeNotFoundException(ErrorType.MOVIE_RECOMMEND_TYPE_NOT_FOUND_ERROR))
                .getRecommendTypeId();

        UserMovieHistory userMovieHistory = UserMovieHistory.of(user.getId(), recommendTypeId, movieId);
        userMovieHistory.updateQuestionAndAnswer(questionAndAnswer);
        RecTypeModel recTypeModel = recommendTypeService.getRecTypeModel(recommendTypeId);
        MovieModel movieModel = movieService.getMovieModel(movieId);
        List<MovieModel> recMovies = movieService.getMovieModelList(movieId, 3);
        userMovieHistoryService.saveUserMovieHistory(userMovieHistory);

        return RecResponse.toResponse(recTypeModel, movieModel, recMovies);
    }
}
