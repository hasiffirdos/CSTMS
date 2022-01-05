CREATE TABLE IF NOT EXISTS `courses`
(
    `id`           INT           NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255),
    `teacher`      INT           NOT NULL,
    `created_at`   DATETIME      NOT NULL,
    `updated_at`   DATETIME      NOT NULL,
    `price`        INT           NOT NULL,
    `rating`       FLOAT         NOT NULL,
    `description`  varchar(1000) NOT NULL,
    `prerequisite` INT           NULL,
    `views`        INT           NOT NULL,
    `content_id`   INT           NULL,
    PRIMARY KEY (`id`)
);