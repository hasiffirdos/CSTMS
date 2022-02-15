CREATE TABLE IF NOT EXISTS users
(
    `id`          INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `active`      BIT          NOT NULL,
    `password`    VARCHAR(255) NOT NULL,
    `role`        VARCHAR(15)  NOT NULL,
    `user_name`   VARCHAR(100) NOT NULL UNIQUE,
    `firstname`   varchar(50),
    `lastname`    varchar(50)  NOT NULL,
    `email`       varchar(50)  NOT NULL UNIQUE,
    `phone`       varchar(13)  NOT NULL,
    `signup_date` DATETIME     NOT NULL,
    `dob`         DATETIME     NOT NULL,
    `age`         INT          NOT NULL
);