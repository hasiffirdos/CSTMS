CREATE TABLE IF NOT EXISTS `students`
(
    `id`          INT          NOT NULL PRIMARY KEY,
#     `firstname`   varchar(50),
#     `lastname`    varchar(50)  NOT NULL,
#     `email`       varchar(50)  NOT NULL,
#     `phone`       varchar(13)  NOT NULL,
#     `signup_date` DATETIME     NOT NULL,
#     `dob`         DATETIME     NOT NULL,
#     `age`         INT          NOT NULL,
    constraint usersPkAsFKInStudents
        foreign key (id) references cms_db.users (id)
);