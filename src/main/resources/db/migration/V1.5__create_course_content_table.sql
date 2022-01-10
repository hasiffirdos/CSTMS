CREATE TABLE `CourseContent`
(
    `id`           INT          NOT NULL,
    `file_name`    varchar(255) NOT NULL,
    `filetype`     varchar(255) NOT NULL,
    `upload_date`  DATETIME     NOT NULL,
    `created_date` DATETIME     NOT NULL,
    PRIMARY KEY (`id`)
);