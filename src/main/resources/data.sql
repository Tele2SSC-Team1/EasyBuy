insert into category(ID, DESCRIPTION, category_name, parent_id) values (1, 'Mobile Phones & Tech', 'Mobile phone & Tech', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (42, 'Mobile Phones & Tech', 'Mobile phone', 1);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (2, 'Mobile Phones & Tech', 'Cover', 1);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (3, 'Mobile Phones & Tech', 'Batteries', 1);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (4, 'Mobile Phones & Tech', 'Accesories', 1);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (43, 'Mobile Phones & Tech', 'Router & Tech', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (5, 'Mobile Phones & Tech', 'Router', 43);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (6, 'Mobile Phones & Tech', 'Accesories', 43);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (7, 'Computer Tech', 'Computers', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (8, 'Computer Tech', 'Notebok', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (9, 'Computer Tech', 'Stationary simple', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (10, 'Computer Tech', 'Stationary work', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (11, 'Computer Tech', 'Play', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (12, 'Computer Tech', 'Accesories', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (13, 'Computer Tech', 'Pads', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (14, 'Computer Tech', 'iPads', 13);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (15, 'Computer Tech', 'Drowing pads', 13);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (16, 'Computer Tech', 'E-Books', 13);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (17, 'Computer Tech', 'Accesories', 13);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (18, 'Computer Tech', 'Programms', 7);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (44, 'Home Tech', 'TV', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (19, 'Home Tech', 'TV', 44);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (20, 'Home Tech', 'Tuner', 19);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (21, 'Home Tech', 'Accesories', 19);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (22, 'Home Tech', 'House Hold', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (23, 'Home Tech', 'Vacuum cleaners', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (24, 'Home Tech', 'Fridges', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (25, 'Home Tech', 'Irons', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (26, 'Home Tech', 'Clearning staffs', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (27, 'Home Tech', 'Kitchen Tech', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (28, 'Home Tech', 'Kitchen staffs', 22);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (29, 'Home', 'Furniture', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (30, 'Home', 'Living room', 29);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (31, 'Home', 'Kitchen', 29);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (32, 'Home', 'Badroom', 29);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (33, 'Home', 'Bathroom', 29);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (34, 'Home', 'Other room', 29);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (35, 'Home', 'Tools', null);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (36, 'Home', 'Garage Light', 35);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (37, 'Home', 'Garage Home', 35);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (38, 'Home', 'Garage Hard', 35);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (39, 'Home', 'Garden Light', 35);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (40, 'Home', 'Garden Hard', 35);
insert into category(ID, DESCRIPTION, category_name, parent_id) values (41, 'Home', 'Garden Tech', 35);

insert into role (id, role_name) values (1, 'admin');
insert into user (id, full_name, email, password, PHONE_NUM, rating) values (1, 'Admin Admin', 'admin@gmail.com', 'admin', 5555555, 0);
insert into user_role(user_id, role_id) values (1,1);
insert into role (id, role_name) values (2, 'user');
insert into user (id, full_name, email, password, PHONE_NUM, rating) values (2, 'Simple User', 'user@domain.com', 'user', 5555555, 0);
insert into user_role(user_id, role_id) values (2,2);
insert into role (id, role_name) values (3, 'seller');
insert into user (id, full_name, email, password, PHONE_NUM, rating) values (3, 'Seller Seller', 'seller@gmail.com', 'seller', 5555555, 0);
insert into user_role(user_id, role_id) values (3,3);
insert into user_role(user_id, role_id) values (1,3);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (1, 50, 'CEDO03', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB DUAL BLACK ENG/RU', 159, 1, 'AVAILABLE', 42, '951838_xl.jpg', 3, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (2, 150, 'CEDO04', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI NOTE 4 3GB RAM 32GB BLACK ENG/RU', 175, 1, 'AVAILABLE', 42, '743911_xl.jpg', 4, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (3, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE', 42, '872085_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (4, 30, 'CEDO05', 'New mobile phone from XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 128GB WHITE/GOLD ENG/RU', 146, 1, 'AVAILABLE', 42, '786033_xl.jpg', 1, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (5, 150, 'SMSGA3', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 180, 1, 'AVAILABLE', 42, '950257_xl.jpg', 3, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (6, 150, 'SMSGA5', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 180, 1, 'AVAILABLE', 42, '826372_xl.jpg', 0, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (7, 200, 'SMSGS3', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 250, 1, 'AVAILABLE', 42, '871377_xl.jpg', 4, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (8, 300, 'SMSGS4', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 350, 1, 'AVAILABLE', 42, '385458_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (9, 400, 'SMSGS5', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 450, 1, 'AVAILABLE', 42, '718066_xl.jpg', 2, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (10, 450, 'SMSGS6', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 500, 1, 'AVAILABLE', 42, '874727_xl.jpg', 3, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (11, 500, 'SMSGS8', 'New mobile phone from Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 600, 1, 'AVAILABLE', 42, '409411_xl.jpg', 3, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (12, 350, 'APPLS5', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 16GB SINGLE BLACK ENG/RU', 400, 1, 'AVAILABLE', 42, '746042_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (13, 400, 'APPLS6', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 32GB SINGLE BLACK ENG/RU', 500, 1, 'AVAILABLE', 42, '801143_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (14, 500, 'APPLS6.5', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 16GB SINGLE BLACK ENG/RU', 600, 1, 'AVAILABLE', 42, '743950_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (15, 550, 'APPLS7', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 16GB SINGLE BLACK ENG/RU', 700, 1, 'AVAILABLE', 42, '800196_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (16, 600, 'APPLS8', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 16GB SINGLE BLACK ENG/RU', 800, 1, 'AVAILABLE', 42, 'pti_384_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (17, 1000, 'APPLSX', 'New mobile phone from APPLE', 'APPLE', 'APPLE 4X 16GB SINGLE BLACK ENG/RU', 1100, 1, 'AVAILABLE', 42, '704132_xl.jpg', 3, 1);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (18, 7, 'CEDO05', 'New cover for XIAOMI', 'XIAOMI', 'Cover XIAOMI REDMI 4X 32GB WHITE/GOLD ENG/RU', 14, 1, 'AVAILABLE', 2, '648995_xl.jpg', 2, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (19, 8, 'CEDO05', 'New cover for XIAOMI', 'XIAOMI', 'Cover XIAOMI REDMI 4X 128GB WHITE/GOLD ENG/RU', 14, 1, 'AVAILABLE', 2, '314373_xl.jpg', 4, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (20, 15, 'SMSGA3', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 18, 1, 'AVAILABLE', 2, '800175_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (21, 15, 'SMSGA5', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 18, 1, 'AVAILABLE', 2, '422224_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (22, 17, 'SMSGS3', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 25, 1, 'AVAILABLE', 2, '800150_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (23, 17, 'SMSGS4', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 35, 1, 'AVAILABLE', 2, '807289_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (24, 20, 'SMSGS5', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 45, 1, 'AVAILABLE', 2, '796312_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (25, 45, 'SMSGS6', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 50, 1, 'AVAILABLE', 2, '834518_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (26, 35, 'SMSGS8', 'New cover for Samsung', 'Samsung', 'Cover Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 40, 1, 'AVAILABLE', 2, '242389_xl.jpg', 5, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (27, 30, 'APPLS5', 'New cover for APPLE', 'APPLE', 'Cover APPLE 4X 16GB SINGLE BLACK ENG/RU', 35, 1, 'AVAILABLE', 2, '800800_xl.jpg', 3, 1);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (28, 40, 'APPLSX-1', 'New cover for APPLE', 'APPLE', 'Cover APPLE 4X 32GB SINGLE BLACK ENG/RU', 45, 1, 'AVAILABLE', 2, '893340_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (29, 50, 'APPLSX', 'New cover for APPLE', 'APPLE', 'Cover APPLE 4X 16GB SINGLE BLACK ENG/RU', 70, 1, 'AVAILABLE', 2, '834570_xl.jpg', 0, 0);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (30, 5, 'CEDO03',  'New battery XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB DUAL BLACK ENG/RU', 8, 1, 'AVAILABLE', 3, '794153_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (31, 450, 'SMSGS6', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 50, 1, 'AVAILABLE', 3, '829523_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (32, 10, 'CEDO04', 'New battery XIAOMI', 'XIAOMI', 'XIAOMI REDMI NOTE 4 3GB RAM 32GB BLACK ENG/RU', 15, 1, 'AVAILABLE', 3, '814311_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (33, 30, 'CEDO05', 'New battery XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 32GB WHITE/GOLD ENG/RU', 35, 1, 'AVAILABLE', 3, '181046_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (34, 30, 'CEDO05', 'New battery XIAOMI', 'XIAOMI', 'XIAOMI REDMI 4X 128GB WHITE/GOLD ENG/RU', 38, 1, 'AVAILABLE', 3, '133666_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (35, 10, 'SMSGA3', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 20, 1, 'AVAILABLE', 3, '387017_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (36, 10, 'SMSGA5', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 20, 1, 'AVAILABLE', 3, '183846_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (37, 20, 'SMSGS3', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 25, 1, 'AVAILABLE', 3, '576372_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (38, 30, 'SMSGS4', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 35, 1, 'AVAILABLE', 3, '317036_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (39, 40, 'SMSGS5', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 45, 1, 'AVAILABLE', 3, '277552_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (40, 50, 'SMSGS8', 'New battery Samsung', 'Samsung', 'Samsung REDMI 4X 16GB SINGLE BLACK ENG/RU', 60, 1, 'AVAILABLE', 3, '575943_xl.jpg', 0, 0);

insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (41, 10, 'SSGA5', 'Shtativ', 'Samsung', '2M', 20, 1, 'AVAILABLE', 4, '688500_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (42, 200, 'XXPP', 'Linza', 'Samsung', '2.0-8.0mm zoom', 250, 1, 'AVAILABLE', 4, '240674_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (43, 30, 'MMSGS4', 'Shtativ', 'Samsung', 'Samsung Shtativ white', 35, 1, 'AVAILABLE', 4, '58719_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (44, 40, 'GGS5', 'Hand free', 'Samsung', 'Samsung Hand Free steel', 45, 1, 'AVAILABLE', 4, '459095_xl.jpg', 0, 0);


insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (45, 30, 'MMSGS4', 'Router', 'NNN', 'Wi-Fi open wide', 35, 1, 'AVAILABLE', 5, '824564_xl.jpg', 0, 0);
insert into goods(ID, AMOUNT, CODE, DESCRIPTION, MANUFACTURER, NAME, PRICE, SELLER_ID, STATUS, CATEGORY_ID, IMAGE_FILE_NAME, rating, votes) values (46, 40, 'GGS5', 'Cable', 'APPLE', 'APPLE good cable', 45, 1, 'AVAILABLE', 6, '644717_xl.jpg', 0, 0);
