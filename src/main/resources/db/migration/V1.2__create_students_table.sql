CREATE TABLE IF NOT EXISTS `students`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `firstname`   varchar(50),
    `lastname`    varchar(50)  NOT NULL,
    `email`       varchar(50)  NOT NULL UNIQUE,
    `password`    varchar(255) NOT NULL,
    `phone`       varchar(13)  NOT NULL,
    `role`        varchar(50)  NOT NULL,
    `signup_date` DATETIME     NOT NULL,
    `dob`         DATETIME     NOT NULL,
    `age`         INT          NOT NULL,
    PRIMARY KEY (`id`)
);