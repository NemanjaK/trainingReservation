use trainingreservationv;

insert into user(name, last_name, password, email, phone_number, role, training_type) VALUES ('Petar', 'Petrovic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'pera@gmail.com', '+566181', 1, 'CROSSFIT');
insert into user(name, last_name, password, email, phone_number, role, training_type) VALUES ('Milos', 'Milosevic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'miki@gmail.com', '+848666', 2, 'CROSSFIT');
insert into user(name, last_name, password, email, phone_number, role, training_type) VALUES ('Uros', 'Milenkovic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'uki@gmail.com', '+566181', 3, 'CROSSFIT');

insert into training(date_of_training, description) values ('2021-02-11','Grace');
insert into training(date_of_training, description) values ('2022-02-11','Fran');

insert into result(numbers_ofrounds, time, user_id, training_id) values ('4', '20:00', 1, 1);

insert into term(occupancy, time, training_id) values (20, '2022-02-11 12:00:00', 1);
insert into term(occupancy, time, training_id) values (20, '2022-02-11 12:00:00', 1);

insert into reservation(term_id) values (1);
insert into reservation(term_id) values (2);