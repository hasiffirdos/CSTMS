create table if not exists users
(
    id        integer not null auto_increment,
    active    bit     not null,
    password  varchar(255),
    roles     varchar(50),
    user_name varchar(100),
    primary key (id)
);
INSERT INTO users (active, password, roles, user_name) VALUES (true,'$2a$10$cqFno5hmsI/JsmR7lUoF7eUgQhFulv6ZN.9GE14irg5v3JM7t6zb2',
        'ADMIN','asiffirdos')