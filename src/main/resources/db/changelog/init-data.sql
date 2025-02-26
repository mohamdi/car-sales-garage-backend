--liquibase formatted sql

--changeset Mohamdi:init-data/1
INSERT INTO car_make(id, name)
VALUES (1, 'Toyota'),
         (2, 'Honda'),
         (3, 'Ford'),
         (4, 'Chevrolet'),
         (5, 'Nissan');

INSERT INTO car_model(id, name, make_id)
VALUES (1, 'Corolla', 1),
         (2, 'Civic', 2),
         (3, 'F-150', 3),
         (4, 'Silverado', 4),
         (5, 'Altima', 5);

