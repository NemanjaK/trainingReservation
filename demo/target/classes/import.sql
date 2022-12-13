use trainingreservationdb;

insert into user(name, last_name, password, email, phone_number, role, training_type, status) VALUES ('Petar', 'Petrovic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'pera@gmail.com', '+566181', 1, 1, 1);
insert into user(name, last_name, password, email, phone_number, role, training_type, status) VALUES ('Milos', 'Milosevic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'miki@gmail.com', '+848666', 2,  2, 1);
insert into user(name, last_name, password, email, phone_number, role, training_type, status) VALUES ('Uros', 'Milenkovic', '$2a$10$VGMxw/DcBvHvnX4RmwLSt.x.gxR9QiN0CSsrntduLa2a8o3JmJgBC', 'uki@gmail.com', '+566181', 3, 1, 1);
--
insert into training(date_of_training, description, type_of_training) values ('2021-02-11','Grace', 1);
insert into training(date_of_training, description, type_of_training) values ('2022-02-11','Fran', 1);
--
insert into result(numbers_ofrounds, time, user_id) values ('4', '20:00', 1);
--
insert into term(occupancy, time, type_of_training) values (20, '2022-02-11 12:00:00', 1);
insert into term(occupancy, time, type_of_training) values (20, '2022-02-11 12:00:00', 1);
--
insert into reservation(term_id) values (1);
insert into reservation(term_id) values (2);