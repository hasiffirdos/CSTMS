INSERT INTO users (id,active, password, role, user_name, firstname, lastname, email, phone, signup_date, dob, age)
VALUES (1,true, '$2a$10$cqFno5hmsI/JsmR7lUoF7eUgQhFulv6ZN.9GE14irg5v3JM7t6zb2',
        'ADMIN', 'asiffirdos','asif','firdos','asif@mail.com','+923461398093',CURDATE(),CURDATE(),28);

INSERT INTO admins (id) value (1);