CREATE TABLE IF NOT EXISTS users
(
    id        INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    active    BIT     NOT NULL ,
    password  VARCHAR(255),
    role     VARCHAR(15),
    user_name VARCHAR(100)


);
INSERT INTO users (active, password, role, user_name)
VALUES (true,'$2a$10$cqFno5hmsI/JsmR7lUoF7eUgQhFulv6ZN.9GE14irg5v3JM7t6zb2',
        'ADMIN','asiffirdos')