CREATE TABLE cobrancas (
    id SERIAL PRIMARY KEY,
    assinatura_id INT NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    vencimento TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    data_criacao TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    data_atualizacao TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_assinatura FOREIGN KEY (assinatura_id) REFERENCES assinaturas(id)
);