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
        return new RecResponse(
                new RecTypeModel(
                        1L,
                        "방구석 액션 전문가",
                        List.of("화끈함", "미침"),
                        "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/component/%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%89%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AB%E1%84%80%E1%85%A1.png"
                ),
                new MovieModel(
                        1L,
                        "영화",
                        "plot",
                        "reason",
                        "감독 sad | 각본 ㅁㄴㅇ | 제작 ㅁㄴㅇ",
                        "sad",
                        "ㅁ내,",
                        "ㅁㄴㅇ,",
                        3,
                        "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/etc/Rectangle+35.png"
                ),
                List.of(
                        new MovieModel(
                                1L,
                                "영화",
                                "plot",
                                "reason",
                                "감독 sad | 각본 ㅁㄴㅇ | 제작 ㅁㄴㅇ",
                                "sad",
                                "ㅁ내,",
                                "ㅁㄴㅇ,",
                                3,
                                "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/etc/Rectangle+35.png"
                        ),
                        new MovieModel(
                                1L,
                                "영화",
                                "plot",
                                "reason",
                                "감독 sad | 각본 ㅁㄴㅇ | 제작 ㅁㄴㅇ",
                                "sad",
                                "ㅁ내,",
                                "ㅁㄴㅇ,",
                                3,
                                "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/etc/Rectangle+35.png"
                        ),
                        new MovieModel(
                                1L,
                                "영화",
                                "plot",
                                "reason",
                                "감독 sad | 각본 ㅁㄴㅇ | 제작 ㅁㄴㅇ",
                                "sad",
                                "ㅁ내,",
                                "ㅁㄴㅇ,",
                                3,
                                "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/etc/Rectangle+35.png"
                        )
                )
        );
    }

    public RecResponse getV2(AuthUser user, RecRequest request) {
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
        List<MovieModel> recMovies = movieService.getMovieModelList(movieId);
        userMovieHistoryService.saveUserMovieHistory(userMovieHistory);

        return RecResponse.toResponse(recTypeModel, movieModel, recMovies);
    }
}
