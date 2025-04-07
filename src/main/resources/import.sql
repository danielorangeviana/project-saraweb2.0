INSERT INTO tb_user (name, cpf, email, password) VALUES ('Daniel Orange', '829.974.070-36', 'danieloviana@gmail.com', '$2a$10$4ipAszafmeE7pqOB55.kVea.ySODwaT5lSoWxGyNcRdRkvtc8F5xq');
INSERT INTO tb_user (name, cpf, email, password) VALUES ('Dryele Ribeiro', '478.377.550-81', 'dryele@gmail.com', '$2a$10$4ipAszafmeE7pqOB55.kVea.ySODwaT5lSoWxGyNcRdRkvtc8F5xq');
INSERT INTO tb_user (name, cpf, email, password) VALUES ('Deivson Correa', '462.501.020-94', 'deivson@gmail.com', '$2a$10$4ipAszafmeE7pqOB55.kVea.ySODwaT5lSoWxGyNcRdRkvtc8F5xq');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

INSERT INTO tb_book (number_book, number_of_page) VALUES ('1', '30');
INSERT INTO tb_book (number_book, number_of_page) VALUES ('2', '30');

INSERT INTO tb_page_book (page_number, book_id) VALUES ('10', '1');
INSERT INTO tb_page_book (page_number, book_id) VALUES ('20', '2');

INSERT INTO tb_parent (father, mother) VALUES ('Daniel Orange', 'Dryele Alcantara');
INSERT INTO tb_parent (father, mother) VALUES ('Wylds Ribeiro', 'Priscila Ribeiro');
INSERT INTO tb_parent (father, mother) VALUES ('Igor Medella', 'Julia Eni');
INSERT INTO tb_parent (father, mother) VALUES ('Joaquim Barbosa', 'Daniela Xavier');
INSERT INTO tb_parent (father, mother) VALUES ('Josenildo Cordeiro', 'Maria Delmar');

INSERT INTO tb_godparent (godfather, godmother) VALUES ('Thiago Ribeiro', 'Marcela Gomes');
INSERT INTO tb_godparent (godfather, godmother) VALUES ('Wesley Guimaraes', 'Betina Lourdes');
INSERT INTO tb_godparent (godfather, godmother) VALUES ('Vagner Martins', 'Ludmilla Bitencourt');

INSERT INTO tb_celebrant (name, religious_title) VALUES ('Jerominho da Silva Santos', '1');
INSERT INTO tb_celebrant (name, religious_title) VALUES ('Pedro Alves da Cunha', '2');

INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('101', 'Sofia Alcantara', DATE '2023-03-23', DATE '2024-03-23', '1', '1', '1', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('102', 'Mateus Orange', DATE '2023-06-07', DATE '2024-06-07', '1', '2', '1', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('103', 'Pedro Henrique', DATE '2023-07-14', DATE '2024-07-14', '2', '3', '1', '2');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('104', 'João Guilherme', DATE '2023-08-25', DATE '2024-08-25', '3', '1', '2', '2');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('105', 'Bento Viana', DATE '2023-09-02', DATE '2024-09-02', '4', '2', '2', '1');
INSERT INTO tb_baptism_data (number_term, name_children, date_birth, date_baptism, parent_id, godparent_id, page_book_id, celebrant_id) VALUES ('106', 'Maria Clara', DATE '2023-10-22', DATE '2024-10-22', '5', '3', '2', '2');
