CREATE TABLE renters_tb
(
    id         SERIAL       NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(50)  NOT NULL,
    telephone  VARCHAR(20)  NOT NULL,
    address    VARCHAR(255) NOT NULL,
    cpf        varchar(20),

    CONSTRAINT renters_tb_pk_id PRIMARY KEY (id)
);