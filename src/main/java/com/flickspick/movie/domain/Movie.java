package com.flickspick.movie.domain;

import com.flickspick.common.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String plot;

    private String reason;

    private String director;

    private String producer;

    private String scenario;

    private Integer grade;

    private Long recommendTypeId;

    @Column(name = "image_url")
    private String imageUrl;

    public String getPeople() {
        return "감독 " + director + " | 각본 " + scenario + " | 제작 " + producer;
    }
}
