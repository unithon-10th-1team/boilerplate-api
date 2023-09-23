package com.flickspick.batch;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.flickspick.client.chatgpt.ChatGPTClient;
import com.flickspick.client.dto.ResponseContent;
import com.flickspick.client.dto.ResponseMessage;
import com.flickspick.movie.domain.Movie;
import com.flickspick.movie.infrastructure.MovieRepository;
import com.flickspick.movie_recommendtype.domain.MovieRecommendType;
import com.flickspick.movie_recommendtype.infrastructure.MovieRecommendTypeRepository;
import com.flickspick.user_movie_history.domain.MovieResult;
import com.flickspick.user_movie_history.domain.UserMovieHistory;
import com.flickspick.user_movie_history.infrastructure.UserMovieHistoryRepository;
import com.flickspick.ott.domain.Ott;
import com.flickspick.ott.infrastructure.OttRepository;
import com.flickspick.question.domain.Question;
import com.flickspick.question.domain.QuestionAnswer;
import com.flickspick.question.repository.QuestionAnswerRepository;
import com.flickspick.question.repository.QuestionRepository;
import com.flickspick.rec.dto.RecRequest;
import com.flickspick.recommendtype.domain.RecommendType;
import com.flickspick.recommendtype.infrastructure.RecommendTypeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchService {
	private final UserMovieHistoryRepository userMovieHistoryRepository;
	private final MovieRecommendTypeRepository movieRecommendTypeRepository;
	private final RecommendTypeRepository recommendTypeRepository;
	private final MovieRepository movieRepository;
	private final OttRepository ottRepository;
	private final QuestionRepository questionRepository;
	private final QuestionAnswerRepository questionAnswerRepository;
	private final ChatGPTClient chatGPTClient;

	public void batch() {
		log.info("start batch job");
		List<Ott> ottList = ottRepository.findAll();
		List<Question> questionList = questionRepository.findAll();
		AtomicInteger count = new AtomicInteger(0);
		for (Question question: questionList) {
			List<QuestionAnswer> questionAnswerList = questionAnswerRepository.findAllByQuestionId(question.getId());
			for (QuestionAnswer questionAnswer: questionAnswerList) {
				List<RecRequest.QuestionModel> answers = List.of(new RecRequest.QuestionModel(question.getId(), questionAnswer.getId()));
				for (Ott ott: ottList) {
					List<Long> ottIds = List.of(ott.getId());
					try {
						count.addAndGet(1);
						ResponseMessage responseMessage = chatGPTClient.query(answers, ottIds);
						ResponseContent responseContent = responseMessage.getContent();
						RecommendType recommendType = recommendTypeRepository.findByRecommendType(
										responseContent.getRecommend_type())
								.orElseThrow(() -> new IllegalArgumentException("RecommendType이 없습니다."));
						Movie movie = Movie.builder()
								.title(responseContent.getTitle())
								.reason(responseContent.getReason())
								.plot(responseContent.getPlot())
								.recommendTypeId(recommendType.getId())
								.build();
						movieRepository.save(movie);
						UserMovieHistory userMovieHistory = userMovieHistoryRepository.findByRecommendTypeIdAndMovieId(
										recommendType.getId(), movie.getId())
								.orElseGet(() -> {
									UserMovieHistory newMovieResult = UserMovieHistory.builder()
											.recommendTypeId(recommendType.getId())
											.movieId(movie.getId())
											.build();
									return userMovieHistoryRepository.save(newMovieResult);
								});
						userMovieHistory.addQuestionAndAnswer(question.getId(), questionAnswer.getId());
						userMovieHistoryRepository.save(userMovieHistory);

						MovieRecommendType movieRecommendType = MovieRecommendType.builder()
								.recommendTypeId(recommendType.getId())
								.movieId(movie.getId())
								.build();
						movieRecommendTypeRepository.save(movieRecommendType);
						log.info("성공 질문 count: {}, id: {}, 답변id: {}, ottId: {}", count.get(), question.getId(), questionAnswer.getId(), ott.getId());
					} catch (Exception e) {
						if (e instanceof IllegalArgumentException) {
							log.info("RecommendType이 없습니다.");
						}
						log.error("실패 질문 count: {}, id: {}, 답변id: {}, ottId: {}", count.get(), question.getId(), questionAnswer.getId(), ott.getId());
					}
					try {
						Thread.sleep(22000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		log.info("finish batch job");
	}

}
