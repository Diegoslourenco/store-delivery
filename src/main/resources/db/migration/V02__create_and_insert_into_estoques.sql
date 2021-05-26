CREATE TABLE estoques (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	produto_id BIGINT(20) NOT NULL,
	quantity INT(11) NOT NULL,
	selling_price DECIMAL(10,2) NOT NULL,
	FOREIGN KEY (produto_id) REFERENCES produtos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# estoques

INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (1, 20, 2.99);
INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (2, 15, 3.89);
INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (3, 30, 5.99);
INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (4, 35, 13.29);
INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (5, 50, 6.99);
INSERT INTO estoques (`produto_id`, `quantity`, `selling_price`) VALUES (6, 10, 2.99);



