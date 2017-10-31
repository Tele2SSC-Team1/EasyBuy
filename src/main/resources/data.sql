insert into role (id, role_name) values (1, 'admin');

insert into user (id, full_name, email, password, PHONE_NUM)
values (1, 'Admin Admin', 'admin@gmail.com', 'admin', 5555555);

insert into user_role(user_id, role_id) values (1,1);

insert into role (id, role_name) values (2, 'user');

insert into user (id, full_name, email, password, PHONE_NUM)
values (2, 'Simple User', 'user@domain.com', 'user', 5555555);

insert into user_role(user_id, role_id) values (2,2);

insert into role (id, role_name) values (3, 'seller');

insert into user (id, full_name, email, password, PHONE_NUM)
values (3, 'Seller', 'seller@gmail.com', 'seller', 5555555);

insert into user_role(user_id, role_id) values (3,3);

