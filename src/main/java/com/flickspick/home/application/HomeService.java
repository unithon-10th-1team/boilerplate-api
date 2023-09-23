package com.flickspick.home.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.user.UserNotFoundException;
import com.flickspick.home.dto.response.MyHomeResponse;
import com.flickspick.home.dto.response.MyProfileResponse;
import com.flickspick.movie.application.MovieService;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.ott.model.OttModel;
import com.flickspick.recommendtype.application.RecommendTypeService;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.user.infrastructure.UserRepository;
import com.flickspick.user.model.UserModel;
import com.flickspick.user_movie_history.application.UserMovieHistoryService;
import com.flickspick.user_movie_history.domain.UserMovieHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final UserRepository userRepository;
    private final UserMovieHistoryService userMovieHistoryService;
    private final RecommendTypeService recommendTypeService;
    private final MovieService movieService;

    public MyHomeResponse getHome(AuthUser authUser) {
        var user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new UserNotFoundException(ErrorType.USER_NOT_FOUND_ERROR));
        UserModel userModel = UserModel.from(user);
        UserMovieHistory userMovieHistory = userMovieHistoryService.getRecentHistory(user.getId());
        RecTypeModel recTypeModel = recommendTypeService.getRecTypeModel(userMovieHistory.getRecommendTypeId());

        List<String> tags = recTypeModel.getTags();

        List<MovieModel> similarMovies = movieService.getMovieModelList(userMovieHistory.getMovieId(), 2);
        MovieModel movieModel = movieService.getMovieModel(userMovieHistory.getMovieId());
        similarMovies.add(movieModel);

        List<MovieModel> differentMovies = movieService.getMovieModelList(userMovieHistory.getMovieId(), 3);

        return MyHomeResponse.toResponse(userModel, List.of(recTypeModel), tags, similarMovies, differentMovies);
    }

    public MyProfileResponse getMyProfile(AuthUser authUser) {
        var user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new UserNotFoundException(ErrorType.USER_NOT_FOUND_ERROR));

        return new MyProfileResponse(
                UserModel.from(user),
                List.of(
                        new OttModel(
                                1L,
                                "name",
                                "name",
                                "name",
                                "image"
                        )
                ),
                List.of(
                        new RecTypeModel(
                                1L,
                                "방구석 액션 전문가",
                                List.of("화끈함", "미침"),
                                "https://unithon-bucket.s3.ap-northeast-2.amazonaws.com/component/%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%89%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%A5%E1%86%AB%E1%84%86%E1%85%AE%E1%86%AB%E1%84%80%E1%85%A1.png"
                        )
                ),
                List.of("tags", "tagsss"),
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
}
