-- Database data init
INSERT INTO DAY (day_id, name) VALUES (1, 'Monday'), (11, 'Tuesday'), (21, 'Wednesday'), (31, 'Thursday'), (41, 'Friday'), (51, 'Saturday'), (61, 'Sunday');
INSERT INTO HOUR (hour_id, start, end) VALUES (1, '8:15', '9:45'), (11, '10:00', '11:30'), (21, '11:45', '13:15'), (31, '13:30', '15:00'), (41, '15:15', '16:45'), (51, '17:00', '18:30'), (61, '18:45', '20:15');
INSERT INTO ROLES (role_id, role) VALUES (0, 'ADMIN'), (1, 'USER');
INSERT INTO ROOM (number) VALUES (NULL), ('202'), ('206'), ('303'), ('304'), ('305');
INSERT INTO SUBJECT (subject_name) VALUES (NULL), ('Projekt dyplomowy 1 (L)'), ('Projekt dyplomowy 1 (W)'), ('Algebra liniowa (W)'), ('Systemy wbudowane (L)'), ('Proj. stron www (L)');
INSERT INTO TEACHER (first_name, last_name, title) VALUES (NULL, NULL, NULL), ('K.', 'Racka', 'mgr'), ('R.', 'Kapturski', 'mgr inz.'), ('S.', 'Wszelak', 'dr inz.'), ('M.', 'Smietanski', 'prof. dr hab.');
INSERT INTO USER (admin, avatar, email, name, password) VALUES (0, NULL, 'admin@test.com', 'ADMIN', '$2y$10$CRo0K7KXCDEeb8hAUI3rze4S3Espxoqut9Ldb6620oDcY9RvHIHrm');
INSERT INTO user_role (user_id, role_id) VALUES (1, 0);
INSERT INTO GROUPS (groups_id, group_name, group_quantity) VALUES (0, NULL, 0);