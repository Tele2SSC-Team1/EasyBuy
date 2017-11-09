insert into category(ID, DESCRIPTION, category_name, sub_category_name) values
    (1, 'Mobile Phones', 'Mobile phone', 'Mobile phone');

insert into category(ID, DESCRIPTION, category_name, sub_category_name) values
    (2, 'TV', 'TV', 'TV');

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
insert into user_role(user_id, role_id) values (1,3);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID) values
    (1, 50, 'CEDO03', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB DUAL BLACK ENG/RU', 159, 1, 'AVAILABLE', 1);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID) values
    (2, 150, 'CEDO04', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI NOTE 4 3GB RAM 32GB BLACK ENG/RU', 175, 1, 'AVAILABLE', 1);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID) values
    (3, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE', 1);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID) values
    (4, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 128GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE', 1);

