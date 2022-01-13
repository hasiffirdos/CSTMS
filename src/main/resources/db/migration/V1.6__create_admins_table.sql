create table if not exists admins
(
    id          integer not null auto_increment,
    age         integer not null,
    dob         datetime,
    email       varchar(50),
    firstname   varchar(50),
    lastname    varchar(50),
    password    varchar(255),
    phone       varchar(13),
    signup_date datetime,
    primary key (id)
);
INSERT INTO admins (age, dob, email, firstname, lastname, password, phone, signup_date)
VALUES (40,CURDATE(),'asif@gmail.com','asif','firdos','$2a$10$cqFno5hmsI/JsmR7lUoF7eUgQhFulv6ZN.9GE14irg5v3JM7t6zb2',
        '+923461398093',curdate())