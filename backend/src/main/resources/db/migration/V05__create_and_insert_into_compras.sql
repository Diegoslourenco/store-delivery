CREATE TABLE compras (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	fornecedor_id BIGINT(20) NOT NULL,
	FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# fornecedores

INSERT INTO compras (`fornecedor_id`) VALUES (1);
INSERT INTO compras (`fornecedor_id`) VALUES (2);
INSERT INTO compras (`fornecedor_id`) VALUES (3);
INSERT INTO compras (`fornecedor_id`) VALUES (4);
INSERT INTO compras (`fornecedor_id`) VALUES (5);
INSERT INTO compras (`fornecedor_id`) VALUES (6);
INSERT INTO compras (`fornecedor_id`) VALUES (1);