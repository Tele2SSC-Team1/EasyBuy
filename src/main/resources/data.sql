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

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS) values
    (1, 50, 'CEDO03', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB DUAL BLACK ENG/RU', 159, 1, 'AVAILABLE');

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS) values
    (2, 150, 'CEDO04', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI NOTE 4 3GB RAM 32GB BLACK ENG/RU', 175, 1, 'AVAILABLE');

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS) values
    (3, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE');

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS) values
    (4, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 128GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE');
