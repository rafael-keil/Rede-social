CREATE TABLE fb_usuario (
	id int NOT NULL,
	apelido varchar(255),
	data_nascimento date NOT NULL,
	email varchar(255) NOT NULL,
	imagem varchar(255),
	nome varchar(255) NOT NULL,
	sobrenome varchar(255) NOT NULL,
	CONSTRAINT fb_usuario_pkey PRIMARY KEY (id)
);


CREATE TABLE fb_entrada_base (
	id uuid NOT NULL,
	"data" timestamp,
	descricao varchar(255),
	autor int NOT NULL,
	imagem varchar,
	CONSTRAINT fb_entrada_base_pkey PRIMARY KEY (id),
	CONSTRAINT autor_id FOREIGN KEY (autor) REFERENCES fb_usuario(id)
);


CREATE TABLE fb_post (
	is_privado bool NOT NULL,
	titulo varchar(255),
	valor float,
	id uuid NOT NULL,
	CONSTRAINT fb_post_pkey PRIMARY KEY (id),
	CONSTRAINT entrada_id FOREIGN KEY (id) REFERENCES fb_entrada_base(id)
);


CREATE TABLE fb_solicitacao (
	is_aceita bool NOT NULL,
	destinatario int NOT NULL,
	remetente int NOT NULL,
	id uuid NOT NULL,
	CONSTRAINT fb_solicitacao_pkey PRIMARY KEY (id),
	CONSTRAINT id_destinatario FOREIGN KEY (destinatario) REFERENCES fb_usuario(id),
	CONSTRAINT id_remetente FOREIGN KEY (remetente) REFERENCES fb_usuario(id)
);


CREATE TABLE fb_comentario (
	id uuid NOT NULL,
	pai_id uuid NOT NULL,
	CONSTRAINT fb_comentario_pkey PRIMARY KEY (id),
	CONSTRAINT id_comentario FOREIGN KEY (id) REFERENCES fb_entrada_base(id),
	CONSTRAINT id_pai FOREIGN KEY (pai_id) REFERENCES fb_entrada_base(id)
);


CREATE TABLE fb_curtida (
	id int NOT NULL,
	autor int NOT NULL,
	pai uuid NOT NULL,
	CONSTRAINT fb_curtida_pkey PRIMARY KEY (id),
	CONSTRAINT id_pai FOREIGN KEY (pai) REFERENCES fb_entrada_base(id),
	CONSTRAINT is_autor FOREIGN KEY (autor) REFERENCES fb_usuario(id)
);