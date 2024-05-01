INSERT INTO tb_user (name, cpf, login, password) VALUES ('Daniel Orange', '829.974.070-36', 'danieldo', '$2a$10$6DdmpWwTTqaS/kRGjFrm5eO6VJc40NRvLExk3B5FMTcW3dB8hHtce');
INSERT INTO tb_user (name, cpf, login, password) VALUES ('Dryele Ribeiro', '478.377.550-81', 'dryeledr', '$2a$10$6DdmpWwTTqaS/kRGjFrm5eO6VJc40NRvLExk3B5FMTcW3dB8hHtce');
INSERT INTO tb_user (name, cpf, login, password) VALUES ('Deivson Correa', '462.501.020-94', 'deivsondc', '$2a$10$6DdmpWwTTqaS/kRGjFrm5eO6VJc40NRvLExk3B5FMTcW3dB8hHtce');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

INSERT INTO tb_book (number_book, number_sheet) VALUES ('1', '10');
INSERT INTO tb_book (number_book, number_sheet) VALUES ('2', '20');

INSERT INTO tb_celebrant (name, religious_title) VALUES ('Jerominho da Silva Santos', '1');
INSERT INTO tb_celebrant (name, religious_title) VALUES ('Pedro Alves da Cunha', '2');

INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('101', 'Sofia Alcantara', DATE '2023-03-23', 'Daniel Orange', 'Dryele Alcantara', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-03-23', '1', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('102', 'Mateus Orange', DATE '2023-06-07', 'Daniel Orange', 'Dryele Alcantara', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-06-07', '1', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('103', 'Pedro Henrique', DATE '2023-07-14', 'Wylds Ribeiro', 'Priscila Ribeiro', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-07-14', '1', '2');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('104', 'João Guilherme', DATE '2023-08-25', 'Igor Medella', 'Julia Eni', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-08-25', '2', '2');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('105', 'Bento Viana', DATE '2023-09-02', 'Joaquim Barbosa', 'Daniela Xavier', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-09-02', '2', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, father, mother, godfather, godmother, date_baptism, book_id, celebrant_id) VALUES ('106', 'Maria Clara', DATE '2023-10-22', 'Josenildo Cordeiro', 'Maria Delmar', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-10-22', '2', '2');
