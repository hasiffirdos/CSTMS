CREATE TABLE IF NOT EXISTS `teaches`
(
    `teacher_id` INT NOT NULL,
    `course_id`  INT NOT NULL,
    CONSTRAINT `fk_teacher` FOREIGN KEY (`teacher_id`)
        REFERENCES teachers (id),
    CONSTRAINT `fk_course` FOREIGN KEY (`course_id`)
        REFERENCES courses (id)


);