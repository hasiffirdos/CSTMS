CREATE TABLE `course_contents`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `filename`    varchar(255) NOT NULL,
    `filetype`    varchar(255) NOT NULL,
    `create_at`   DATETIME     NOT NULL,
    `description` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);