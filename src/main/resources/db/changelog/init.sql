--liquibase formatted sql

--changeset Mohamdi:init/1
-- CREATE SEQUENCE hibernate_sequence START 1000 INCREMENT 1;
CREATE SEQUENCE hibernate_sequence MAXVALUE 999999999 INCREMENT BY 1 START WITH 1000 NOCACHE NOCYCLE;

CREATE TABLE if not exists car_make
(
    id          BIGINT PRIMARY KEY  NOT NULL,
    name        VARCHAR(255) NOT NULL UNIQUE,
    created_at  timestamp,
    updated_at  timestamp
);
create table if not exists car_model
(
    id          BIGINT PRIMARY KEY NOT NULL,
    name        VARCHAR(255) NOT NULL UNIQUE,
    make_id     BIGINT NOT NULL,
    created_at  timestamp,
    updated_at  timestamp,
    foreign key (make_id) references car_make (id)
);

create table if not exists car
(
    id                  BIGINT PRIMARY KEY  NOT NULL,
    make_id             BIGINT NOT NULL,
    model_id            BIGINT NOT NULL,
    registration_date   DATE not null,
    price               DECIMAL NOT NULL,
    fuel_type           VARCHAR(255) NOT NULL,
    mileage             INTEGER NOT NULL,
    transmission        VARCHAR(255) NOT NULL,
    picture             VARCHAR(255) NOT NULL,
    created_at          timestamp,
    updated_at          timestamp,
    foreign key (make_id) references car_make (id),
    foreign key (model_id) references car_model (id)
);
