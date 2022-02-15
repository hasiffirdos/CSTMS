CREATE TABLE IF NOT EXISTS `teachers`
(
    `id`          INT          NOT NULL PRIMARY KEY,
#     `firstname`   varchar(50),
#     `lastname`    varchar(50)  NOT NULL,
#     `email`       varchar(50)  NOT NULL UNIQUE,
#     `phone`       varchar(13)  NOT NULL,
#     `signup_date` DATETIME     NOT NULL,
#     `dob`         DATETIME     NOT NULL,
#     `age`         INT          NOT NULL,
    constraint usersPkAsFKInTeachers
        foreign key (id) references cms_db.users (id)
);