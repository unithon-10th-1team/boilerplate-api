package com.flickspick.movie_result.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.flickspick.common.model.converter.MapConverter;
import com.flickspick.common.model.converter.StringArrayConverter;
import com.flickspick.common.model.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class MovieResult extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = MapConverter.class)
	private Map<Long, Long> questionAndAnswer;

	private Long recommendTypeId;

	private Long movieId;

	public void addQuestionAndAnswer(Long questionId, Long answerId) {
		if (questionAndAnswer == null) {
			questionAndAnswer = new HashMap<>();
		}
		questionAndAnswer.put(questionId, answerId);
	}
}
