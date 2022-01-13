CREATE TABLE IF NOT EXISTS `enrollments`
(
    `student_id` INT NOT NULL,
    `course_id`  INT NOT NULL,
    CONSTRAINT `StudentsPkAsFkInEnrollments`
        FOREIGN KEY (`student_id`) REFERENCES students(id),
    CONSTRAINT `CoursesPkAsFkInEnrollments`
        FOREIGN KEY (`course_id`) REFERENCES courses(id)

);