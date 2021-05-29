CREATE TABLE clientes (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	cpf VARCHAR(18) NOT NULL,
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

# clientes

INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('528.832.640-15', 'Paulo Santos', '1132375869', 'paulosantos@gmail.com', 'Avenida das Camelias', '25', 'Ao lado Correio', 'Jardim dos Sonhos', '15365-852', 'São Paulo', 'SP');
INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('235.471.220-07', 'Diego Lourenço', '1135225869', 'diego.lourenco@gft.com', 'Rua Pedro José', '13', '', 'Bairro das Camélias', '18025-369', 'São Paulo', 'SP');
INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('511.788.300-47', 'Mariana Gonçalves', '1532375869', 'marianagoncalves@gmail.com', 'Avenida Mauro Pedrito', '200', '', 'Jardim Gutierres', '18025-632', 'Sorocaba', 'SP');
INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('846.452.810-89', 'Larissa Medeiros', '11323565869', 'larissa@gmail.com', 'Rua das Flores', '36', '', 'Bairro dos Açores', '96502-369', 'São Paulo', 'SP');
INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('795.456.670-60', 'Flavio Dino', '1832375869', 'flavio@gmail.com', 'Avenida Sérgio Brito', '13', 'Cruzamento Avenida Santos Dumont', 'Jardim Menezes', '85236-985', 'São Carlos', 'SP');
INSERT INTO clientes (`cpf`, `name`, `phone`, `email`, `street`, `number`, `complement`, `district`, `cep`, `city`, `state`) VALUES ('703.752.220-14', 'Poliana Pontes', '4732375869', 'poliana@gmail.com', 'Rua Carlos Pereira', '15', '', 'Jardim da Amizade', '85236-987', 'Penha', 'SC');
