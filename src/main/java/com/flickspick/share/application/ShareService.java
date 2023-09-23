package com.flickspick.share.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.exception.user.UserNotFoundException;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.recommendtype.model.RecTypeModel;
import com.flickspick.share.dto.response.ShareResponse;
import com.flickspick.user.infrastructure.UserRepository;
import com.flickspick.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShareService {
    private final UserRepository userRepository;

    public ShareResponse share(AuthUser authUser) {
        var user = userRepository.findById(authUser.getId())
                .orElseThrow(() -> new UserNotFoundException(ErrorType.USER_NOT_FOUND_ERROR));

        return new ShareResponse(
                UserModel.from(user),
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
