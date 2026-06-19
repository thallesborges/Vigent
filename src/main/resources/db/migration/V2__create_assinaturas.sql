CREATE TABLE assinaturas (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    plano_id INT NOT NULL,
    data_inicio DATE NOT NULL,
    data_termino DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_plano FOREIGN KEY (plano_id) REFERENCES planos(id)
);