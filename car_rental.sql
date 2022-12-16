Drop table if exists car_tab;
CREATE TABLE car_rental.car_tab (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	car_model varchar(512) NOT NULL,
	car_stock BIGINT UNSIGNED DEFAULT 0 NOT NULL,
	car_daily_price DOUBLE DEFAULT 0 NOT NULL,
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	delete_flag TINYINT DEFAULT 0 NOT NULL,
	PRIMARY KEY car_tab_pk(id)  
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='car info tab';

Drop table if exists order_tab;
CREATE TABLE car_rental.order_tab (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	order_num varchar(512) NOT NULL,
	user_id BIGINT UNSIGNED DEFAULT 0 NOT NULL,
	car_id BIGINT UNSIGNED DEFAULT 0 NOT NULL,
	rental_start_time DATETIME NOT null,
	rental_end_time DATETIME NOT null,
	order_price DOUBLE DEFAULT 0 NOT null,
	order_status TINYINT NOT null comment '0-unpaid, 1-paid, 2-canceled, 3-completed',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	delete_flag TINYINT DEFAULT 0 NOT NULL,
	PRIMARY KEY order_tab_pk(id)  
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='car info tab';

Drop table if exists user_tab;
CREATE TABLE car_rental.user_tab (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	user_name varchar(512) NOT NULL,
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	delete_flag TINYINT DEFAULT 0 NOT NULL,
	PRIMARY KEY user_tab_pk(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='car info tab';


INSERT INTO car_rental.car_tab
(id, car_model, car_stock, car_daily_price, create_time, update_time, delete_flag)
VALUES(1, 'Toyota Camry', 2, 200.0, now(), now(), 0),
(2, 'BMW 650', 2, 300.0, now(), now(), 0);


INSERT INTO car_rental.user_tab
(id, user_name, create_time, update_time, delete_flag)
VALUES(1, 'hzg', now(), now(), 0);


