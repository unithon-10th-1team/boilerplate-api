package com.flickspick.recommendtype.domain;

import com.flickspick.common.model.converter.StringArrayConverter;
import com.flickspick.common.model.entity.BaseEntity;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class RecommendType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recommendType;

    @Convert(converter = StringArrayConverter.class)
    private List<String> tags;

    private String imageUrl;
}
