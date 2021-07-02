CREATE TABLE itens_venda (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	produto_id BIGINT(20) NOT NULL,
	venda_id BIGINT(20) NOT NULL,
	quantity INT(11) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (produto_id) REFERENCES produtos(id),
	FOREIGN KEY (venda_id) REFERENCES vendas(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# itens

INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (1, 1, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (1, 2, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (2, 2, 10, 3.89);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (3, 3, 10, 5.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (4, 3, 10, 13.29);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (6, 3, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (4, 4, 10, 13.29);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (5, 5, 10, 6.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (6, 5, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (5, 6, 10, 6.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (6, 6, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (1, 7, 10, 2.99);
INSERT INTO itens_venda (`produto_id`, `venda_id`, `quantity`, `price`) VALUES (6, 7, 10, 2.99);
