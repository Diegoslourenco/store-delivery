CREATE TABLE produtos (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(50) NOT NULL,
	unit VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# produtos

INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Refrigerante Coca-cola', 'Refrigerante sem açucar', 'lata');
INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Leite Jussara', 'Leite longa vida desnatado 1 litro', 'unidade');
INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Maça', 'Maça fuji', 'kg');
INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Biscoito Oreo', 'Biscoito recheado com baunilha original', 'pacote');
INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Arroz Tio João', 'Arroz agulhinha tipo 1 com 2 kg', 'pacote');
INSERT INTO produtos (`name`, `description`, `unit`) VALUES ('Feijão Camil', 'Feijão carioca tipo 1 com 1 kg', 'pacote');
