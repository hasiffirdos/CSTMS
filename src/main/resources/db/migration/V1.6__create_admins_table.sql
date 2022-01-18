create table if not exists admins
(
    id          INT NOT NULL PRIMARY KEY,
    age         INT NOT NULL,
    dob         datetime,
    email       varchar(50),
    firstname   varchar(50),
    lastname    varchar(50),
    phone       varchar(13),
    signup_date datetime,
    constraint usersPkAsFKInAdmins
        foreign key (id) references cms_db.users (id)
);
INSERT INTO admins (id, age, dob, email, firstname, lastname, phone, signup_date)
VALUES (1, 40, CURDATE(), 'asif@gmail.com', 'asif', 'firdos',
        '+923461398093', curdate())