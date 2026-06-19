CREATE TABLE planos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    preco NUMERIC(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',
    criado_em TIMESTAMP NOT NULL
);

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    razao_social VARCHAR(100) NOT NULL UNIQUE,
    nome_fantasia VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    email_principal VARCHAR(100) NOT NULL UNIQUE,
    data_contratacao DATE NOT NULL,
    telefone VARCHAR(20) NOT NULL UNIQUE,
    email_financeiro VARCHAR(100) UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',
    plano_id INT NOT NULL,

    CONSTRAINT fk_planos FOREIGN KEY (plano_id) REFERENCES planos(id)
);