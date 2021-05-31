CREATE TABLE usuarios (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	perfil_id BIGINT(20) NOT NULL,
	cliente_id BIGINT(20),
	email VARCHAR(50) NOT NULL,
	password VARCHAR(255) NOT NULL,
	FOREIGN KEY (perfil_id) REFERENCES perfis(id),
	FOREIGN KEY (cliente_id) REFERENCES clientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# usuarios

INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('admin@admin.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 1, null);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('paulosantos@gmail.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 1);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('diego.lourenco@gft.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 2);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('marianagoncalves@gmail.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 3);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('larissa@gmail.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 4);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('flavio@gmail.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 5);
INSERT INTO usuarios (`email`, `password`, `perfil_id`, `cliente_id`) VALUES ('poliana@gmail.com', '$2a$10$8srgo2/mD7tGBJrBGY88pucu6Rj93OAlf4.wW0qs/gE9LmfdT.dHa', 2, 6);

