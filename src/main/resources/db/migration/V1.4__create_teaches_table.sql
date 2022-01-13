CREATE TABLE IF NOT EXISTS `teaches`
(
    `teacher_id` INT NOT NULL,
    `course_id`  INT NOT NULL,
    CONSTRAINT `teachersPKAsFKInTeaches` FOREIGN KEY (`teacher_id`)
        REFERENCES teachers (id),
    CONSTRAINT `coursesPKAsFKInTeaches` FOREIGN KEY (`course_id`)
        REFERENCES courses (id)
);
