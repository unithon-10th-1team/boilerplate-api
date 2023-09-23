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
