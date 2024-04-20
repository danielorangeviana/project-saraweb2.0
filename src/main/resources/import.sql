INSERT INTO tb_user (name, cpf, login, password) VALUES ('Daniel Orange', '123.456.678-98', 'danieldo', '123456');
INSERT INTO tb_user (name, cpf, login, password) VALUES ('Dryele Ribeiro', '345.123.567-09', 'dryeledr', '654321');
INSERT INTO tb_user (name, cpf, login, password) VALUES ('Deivson Correa', '098.765.432-12', 'deivsondc', '123485');

INSERT INTO tb_book (number_book, number_sheet) VALUES ('1', '10');
INSERT INTO tb_book (number_book, number_sheet) VALUES ('2', '20');

INSERT INTO tb_celebrant (name, religious_title) VALUES ('Jerominho da Silva Santos', '1');
INSERT INTO tb_celebrant (name, religious_title) VALUES ('Pedro Alves da Cunha', '2');

INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('101', 'Sofia Alcantara', DATE '2023-03-23', 'Daniel Orange', 'Dryele Alcantara', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-03-23', '1', '1');
INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('102', 'Mateus Orange', DATE '2023-06-07', 'Daniel Orange', 'Dryele Alcantara', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-06-07', '1', '1');
INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('103', 'Pedro Henrique', DATE '2023-07-14', 'Wylds Ribeiro', 'Priscila Ribeiro', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-07-14', '1', '2');
INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('104', 'João Guilherme', DATE '2023-08-25', 'Igor Medella', 'Julia Eni', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-08-25', '2', '2');
INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('105', 'Bento Viana', DATE '2023-09-02', 'Joaquim Barbosa', 'Daniela Xavier', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-09-02', '2', '1');
INSERT INTO tb_baptism_data (term, name_children, date_birth, father, mother, god_father, god_mother, date_baptism, book_id, celebrant_id) VALUES ('106', 'Maria Clara', DATE '2023-10-22', 'Josenildo Cordeiro', 'Maria Delmar', 'Thiago Ribeiro', 'Marcela Gomes', DATE '2024-10-22', '2', '2');
