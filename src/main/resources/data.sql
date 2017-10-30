insert into role (id, role_name) values (1, 'admin');

insert into user (id, full_name, email, password)
values (1, 'Admin Admin', 'admin@gmail.com', 'admin');

insert into user_role(user_id, role_id) values (1,1);

insert into role (id, role_name) values (2, 'user');

insert into user (id, full_name, email, password)
values (2, 'Simple User', 'user@domain.com', 'user');

insert into user_role(user_id, role_id) values (2,2);

insert into role (id, role_name) values (3, 'seller');

insert into user (id, full_name, email, password)
values (3, 'Seller', 'seller@gmail.com', 'seller');

insert into user_role(user_id, role_id) values (3,3);

