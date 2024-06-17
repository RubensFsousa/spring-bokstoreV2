CREATE TABLE publishers_tb
(
    id         SERIAL      NOT NULL,
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    name       VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    telephone  VARCHAR(20) NOT NULL,
    site       VARCHAR(50),

    CONSTRAINT publishers_tb_pk_id PRIMARY KEY (id)
);