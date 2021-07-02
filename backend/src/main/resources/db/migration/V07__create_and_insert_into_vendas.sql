CREATE TABLE vendas (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	cliente_id BIGINT(20) NOT NULL,
	status VARCHAR(9) NOT NULL,
	FOREIGN KEY (cliente_id) REFERENCES clientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# clientees

INSERT INTO vendas (`cliente_id`, `status`) VALUES (1, 'CONCLUIDO');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (2, 'CONCLUIDO');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (3, 'CONCLUIDO');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (4, 'CONCLUIDO');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (5, 'CONCLUIDO');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (6, 'PENDENTE');
INSERT INTO vendas (`cliente_id`, `status`) VALUES (1, 'PENDENTE');
