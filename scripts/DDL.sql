CREATE TABLE `user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(256) NOT NULL COMMENT '계정명',
    `nickname`    varchar(256) NOT NULL COMMENT '닉네임',
    `password`    varchar(512) NOT NULL COMMENT '패스워드',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';

CREATE TABLE `ott`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(256) NOT NULL COMMENT '이름',
    `name_eng`    varchar(256) NOT NULL COMMENT '영어명',
    `description` varchar(512) NOT NULL COMMENT '설명',
    `image_url`   varchar(512) NOT NULL COMMENT '이미지 url',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';

CREATE TABLE `question`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `answers`     varchar(512) NOT NULL COMMENT '응답',
    `question`    varchar(512) NOT NULL COMMENT '질문',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='질문';

CREATE TABLE `question_answer`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'id',
    `question_id` int          NOT NULL COMMENT '질문',
    `answer`      varchar(512) NOT NULL COMMENT '응답',
    `created_at`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='질문 응답';

CREATE TABLE `movie`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`       varchar(512) DEFAULT NULL COMMENT '제목',
    `plot`        text COMMENT '설명',
    `reason`      text,
    `director`    varchar(512) DEFAULT NULL COMMENT '감독',
    `producer`    varchar(512) DEFAULT NULL COMMENT '제작',
    `scenario`    varchar(512) DEFAULT NULL COMMENT '각본',
    `grade`       int          DEFAULT NULL COMMENT '평점',
    `image_url`   varchar(512) DEFAULT NULL COMMENT '이미지',
    `created_at`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    `modified_at` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='영화';
