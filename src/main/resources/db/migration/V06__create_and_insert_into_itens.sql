CREATE TABLE itens (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	produto_id BIGINT(20) NOT NULL,
	compra_id BIGINT(20) NOT NULL,
	quantity INT(11) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	transaction VARCHAR(6),
	FOREIGN KEY (produto_id) REFERENCES produtos(id),
	FOREIGN KEY (compra_id) REFERENCES compras(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# itens

INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (1, 1, 10, 1.99, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (1, 2, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (2, 2, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (3, 3, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (4, 3, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (6, 3, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (4, 4, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (5, 5, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (6, 5, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (5, 6, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (6, 6, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (1, 7, 10, 10.50, 'COMPRA');
INSERT INTO itens (`produto_id`, `compra_id`, `quantity`, `price`, `transaction`) VALUES (6, 7, 10, 10.50, 'COMPRA');



