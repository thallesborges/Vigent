UPDATE clientes SET cnpj = regexp_replace(cnpj, '[^0-9]', '', 'g');

ALTER TABLE clientes ALTER COLUMN cnpj TYPE VARCHAR(14);