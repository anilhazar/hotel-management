CREATE DATABASE IF NOT EXISTS hotel_management;

USE hotel_management;

CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test123';

GRANT ALL PRIVILEGES ON hotel_management.* TO 'test'@'localhost';

CREATE TABLE IF NOT EXISTS person
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT     NOT NULL,
    name  VARCHAR(25)                           NOT NULL,
    email VARCHAR(30)                           NOT NULL,
    password  VARCHAR(30)                       NOT NULL,
    age   INT,
    role  ENUM ('CUSTOMER', 'MANAGER', 'OWNER') NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    capacity   INT,
    base_price DECIMAL(10, 2) NOT NULL,
    status     ENUM ('EMPTY', 'OCCUPIED', 'UNDER_MAINTENANCE'),
    type       ENUM ('NORMAL', 'SPECIAL')
);

CREATE TABLE IF NOT EXISTS special_room
(
    id BIGINT PRIMARY KEY,
    CONSTRAINT fk_special_room FOREIGN KEY (id) REFERENCES room (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS special_feature
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    special_room_id BIGINT                                                       NOT NULL,
    feature_name    ENUM ('JACUZZI', 'SAUNA', 'MINIBAR', 'BALCONY', 'FIREPLACE') NOT NULL,
    feature_price   DECIMAL(10, 2)                                               NOT NULL,
    CONSTRAINT fk_special_feature_special_room FOREIGN KEY (special_room_id) REFERENCES special_room (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS services
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    room_id       BIGINT                                                         NOT NULL,
    service_name  ENUM ('ROOM_SERVICE', 'LAUNDRY', 'SPA', 'BREAKFAST', 'DINNER') NOT NULL,
    service_price DECIMAL(10, 2)                                                 NOT NULL,
    CONSTRAINT fk_services_room FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservation
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id    BIGINT         NOT NULL,
    room_id        BIGINT         NOT NULL,
    check_in_date  DATE           NOT NULL,
    check_out_date DATE           NOT NULL,
    extended_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_reservation_customer FOREIGN KEY (customer_id) REFERENCES person (id) ON DELETE CASCADE,
    CONSTRAINT fk_reservation_room FOREIGN KEY (room_id) REFERENCES room (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS invoice
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_id BIGINT         NOT NULL,
    total_price    DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_invoice_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (id) ON DELETE CASCADE
);

-- Uygulamayi test etmek icin hazir dummy data olusturan dql script
INSERT INTO person (name, email, age, role)
VALUES ('Alice Manager', 'alice.manager@example.com', 35, 'MANAGER');
INSERT INTO person (name, email, age, role)
VALUES ('Bob Owner', 'bob.owner@example.com', 45, 'OWNER');

INSERT INTO room (capacity, base_price, status, type)
VALUES (2, 100.00, 'EMPTY', 'NORMAL');
INSERT INTO room (capacity, base_price, status, type)
VALUES (4, 200.00, 'EMPTY', 'SPECIAL');

INSERT INTO special_room (id)
VALUES (2);

INSERT INTO special_feature (special_room_id, feature_name, feature_price)
VALUES (2, 'JACUZZI', 50.00);

INSERT INTO services (room_id, service_name, service_price)
VALUES (1, 'ROOM_SERVICE', 50.00);
INSERT INTO services (room_id, service_name, service_price)
VALUES (2, 'SPA', 100.00);

INSERT INTO reservation (customer_id, room_id, check_in_date, check_out_date, extended_price)
VALUES (1, 1, '2024-07-01', '2024-07-05', 500.00);

INSERT INTO invoice (reservation_id, total_price)
VALUES (1, 600.00);
