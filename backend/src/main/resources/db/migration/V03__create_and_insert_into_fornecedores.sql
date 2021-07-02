CREATE TABLE fornecedores (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	cnpj VARCHAR(18) NOT NULL,
	name VARCHAR(50) NOT NULL,
	phone VARCHAR(13) NOT NULL,
	email VARCHAR(50) NOT NULL,
	street VARCHAR(50) NOT NULL,
	number VARCHAR(10) NOT NULL,
	complement VARCHAR(50),
	district VARCHAR(50) NOT NULL,
	cep VARCHAR(9) NOT NULL,
	city VARCHAR(50) NOT NULL,
	state VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# fornecedores

INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('81.458.747/0001-90', 'Márcio e Elza Mercado ME', '1132375869', 'comercial@marcioelza.com', 'Avenida das Camelias', '25', 'Ao lado Correio', 'Jardim dos Sonhos', '15365-852', 'São Paulo', 'SP');
INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('82.518.053/0001-63', 'Grande Mercado Ltda', '1135275869', 'comercial@grandemercado.com', 'Rua Pedro José', '13', '', 'Bairro das Camélias', '18025-369', 'São Paulo', 'SP');
INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('58.082.387/0001-52', 'Vendemos Alimentos SA', '1532375869', 'comercial@vendemosalimentos.com', 'Avenida Mauro Pedrito', '200', '', 'Jardim Gutierres', '18025-632', 'Sorocaba', 'SP');
INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('76.918.007/0001-59', 'Extra Supermercado Ltda', '11323565869', 'comercial@extra.com', 'Rua das Flores', '36', '', 'Bairro dos Açores', '96502-369', 'São Paulo', 'SP');
INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('36.428.848/0001-63', 'Explendor Ltda', '1832375869', 'comercial@explendor.com', 'Avenida Sérgio Brito', '13', 'Cruzamento Avenida Santos Dumont', 'Jardim Menezes', '85236-985', 'Adamantina', 'SP');
INSERT INTO fornecedores (`cnpj`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('84.095.288/0001-15', 'Carrefour Ltda', '4732375869', 'comercial@carrefour.com', 'Rua Carlos Pereira', '15', '', 'Jardim da Amizade', '85236-987', 'Penha', 'SC');
