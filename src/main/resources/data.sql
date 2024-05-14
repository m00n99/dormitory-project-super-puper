INSERT INTO app_user (role, login, password) VALUES ('STUDENT', 'st-120073', '1111');
INSERT INTO app_user (role, login, password) VALUES ('STUDENT', 'st-120074', '1111');
INSERT INTO app_user (role, login, password) VALUES ('STUDENT', 'st-120075', '1111');
INSERT INTO app_user (role, login, password) VALUES ('STUDENT', 'st-120076', '1111');
INSERT INTO app_user (role, login, password) VALUES ('STUDENT', 'st-120077', '1111');
INSERT INTO app_user (role, login, password) VALUES ('ADMIN', '11111', '11111');

INSERT INTO floor (number) VALUES ('1');
INSERT INTO floor (number) VALUES ('2');
INSERT INTO floor (number) VALUES ('3');
INSERT INTO floor (number) VALUES ('4');
INSERT INTO floor (number) VALUES ('5');
INSERT INTO floor (number) VALUES ('6');

INSERT INTO room (number, floor_id, max_count_student) VALUES ('101', 1, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('102', 1, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('103', 1, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('104', 1, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('105', 1, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('201', 2, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('202', 2, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('203', 2, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('204', 2, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('205', 2, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('301', 3, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('302', 3, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('303', 3, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('304', 3, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('305', 3, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('401', 4, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('402', 4, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('403', 4, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('404', 4, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('405', 4, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('501', 5, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('502', 5, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('503', 5, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('504', 5, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('505', 5, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('601', 6, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('602', 6, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('603', 6, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('604', 6, 3);
INSERT INTO room (number, floor_id, max_count_student) VALUES ('605', 6, 3);

INSERT INTO student (student_id, firstname, lastname, middlename, phone, email, gender, room_id, user_id)
VALUES ('st-120073', 'Айнур', 'Закиров', 'Рафисович', '89273586279', 'aunyrzakirov@mail.ru', 'MALE', 30, 1);
INSERT INTO student (student_id, firstname, lastname, middlename, phone, email, gender, user_id)
VALUES ('st-120074', 'Айнур', 'Закиров', 'Рафисович', '89273586280', 'aunyrzakirov1@mail.ru', 'MALE', 2);
INSERT INTO student (student_id, firstname, lastname, middlename, phone, email, gender, user_id)
VALUES ('st-120075', 'Айнур', 'Закиров', 'Рафисович', '89273586281', 'aunyrzakirov2@mail.ru', 'MALE', 3);
INSERT INTO student (student_id, firstname, lastname, middlename, phone, email, gender, user_id)
VALUES ('st-120076', 'Айнур', 'Закиров', 'Рафисович', '89273586283', 'aunyrzakirov3@mail.ru', 'MALE', 4);
INSERT INTO student (student_id, firstname, lastname, middlename, phone, email, gender, user_id)
VALUES ('st-120077', 'Айнур', 'Закиров', 'Рафисович', '89273586284', 'aunyrzakirov4@mail.ru', 'MALE', 5);