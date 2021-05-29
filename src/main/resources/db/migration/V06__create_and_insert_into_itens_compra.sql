CREATE TABLE itens_compra (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	produto_id BIGINT(20) NOT NULL,
	compra_id BIGINT(20) NOT NULL,
	quantity INT(11) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (produto_id) REFERENCES produtos(id),
	FOREIGN KEY (compra_id) REFERENCES compras(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# itens

INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (1, 1, 10, 1.99);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (1, 2, 10, 2.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (2, 2, 10, 2.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (3, 3, 10, 1.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (4, 3, 10, 3.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (6, 3, 10, 2.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (4, 4, 10, 3.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (5, 5, 10, 5.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (6, 5, 10, 3.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (5, 6, 10, 3.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (6, 6, 10, 5.50);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (1, 7, 10, 1.00);
INSERT INTO itens_compra (`produto_id`, `compra_id`, `quantity`, `price`) VALUES (6, 7, 10, 3.99);
