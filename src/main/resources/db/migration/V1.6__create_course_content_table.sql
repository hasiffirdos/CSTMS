CREATE TABLE IF NOT EXISTS `course_contents`
(
    `id`          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `filename`    varchar(255) NOT NULL,
    `filetype`    varchar(10)  NOT NULL,
    `create_at`   DATETIME     NOT NULL,
    `description` varchar(500) NOT NULL,
    `course_id`   INT          NULL,
    CONSTRAINT `coursePkAsFKInCourseContent`
        FOREIGN KEY (`course_id`) REFERENCES courses (id)
);