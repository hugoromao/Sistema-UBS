CREATE TABLE cliente_tipo (
                              id SERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL
);

CREATE TABLE conselho (
                          id SERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL
);

CREATE TABLE conta_status (
                              id SERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL
);

CREATE TABLE permissao (
                           id SERIAL PRIMARY KEY,
                           nome VARCHAR(255) NOT NULL
);

CREATE TABLE especialidades (
                               id SERIAL PRIMARY KEY,
                               nome VARCHAR(255) NOT NULL
);

CREATE TABLE cliente (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         nome_social VARCHAR(255),
                         cpf VARCHAR(14) NOT NULL,
                         data_nascimento DATE NOT NULL,
                         genero VARCHAR(255) NOT NULL,
                         cor_raca VARCHAR(255) NOT NULL,
                         profissao VARCHAR(255),
                         escolaridade VARCHAR(255),
                         estado_civil VARCHAR(255),
                         naturalidade VARCHAR(255),
                         nome_mae VARCHAR(255),
                         nome_pai VARCHAR(255),
                         telefone VARCHAR(15),
                         tipo_id BIGINT REFERENCES cliente_tipo(id),
                         ativo BOOLEAN DEFAULT TRUE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         login VARCHAR(255) NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         foto VARCHAR(255),
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status_id INT REFERENCES conta_status(id)
);

CREATE TABLE usuario_permissao (
                                   id SERIAL PRIMARY KEY,
                                   usuario_id INT REFERENCES usuario(id),
                                   permissao_id INT REFERENCES permissao(id)
);

CREATE TABLE endereco (
                          id SERIAL PRIMARY KEY,
                          rua VARCHAR(255) NOT NULL,
                          complemento VARCHAR(255) NOT NULL,
                          distrito VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255),
                          estado VARCHAR(2),
                          cep VARCHAR(9) NOT NULL,
                          cliente_id INT REFERENCES cliente(id)
);

CREATE TABLE profissional (
                              id SERIAL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              conselho_id INT REFERENCES conselho(id),
                              conselho_estado VARCHAR(255) NOT NULL,
                              numero_registro VARCHAR(255) NOT NULL,
                              especialidade_id INT REFERENCES especialidades(id),
                              ativo BOOLEAN
);

CREATE TABLE cronograma (
                            id SERIAL PRIMARY KEY,
                            descricao VARCHAR(255),
                            data_inicio TIMESTAMP,
                            data_fim TIMESTAMP,
                            profissional_id INT REFERENCES profissional(id)
);

CREATE TABLE consulta_status (
                                 id SERIAL PRIMARY KEY,
                                 nome VARCHAR(255) NOT NULL
);

CREATE TABLE consulta (
                          id SERIAL PRIMARY KEY,
                          cliente_id INT REFERENCES cliente(id),
                          profissional_id INT REFERENCES profissional(id),
                          cronograma_id INT REFERENCES cronograma(id),
                          status_id INT REFERENCES consulta_status(id),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);