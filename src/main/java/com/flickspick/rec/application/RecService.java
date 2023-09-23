package com.flickspick.rec.application;

import com.flickspick.auth.model.AuthUser;
import com.flickspick.movie.model.MovieModel;
import com.flickspick.rec.dto.RecRequest;
import com.flickspick.rec.dto.RecResponse;
import com.flickspick.rec.model.RecTypeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecService {
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
}
