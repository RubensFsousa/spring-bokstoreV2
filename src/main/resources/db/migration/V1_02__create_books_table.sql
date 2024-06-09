CREATE TABLE books_tb
(
    id                 SERIAL      NOT NULL,
    created_at         TIMESTAMP   NOT NULL,
    updated_at         TIMESTAMP   NOT NULL,
    name               VARCHAR(50) NOT NULL,
    author             VARCHAR(50) NOT NULL,
    available_quantity INTEGER     NOT NULL,
    launch_date        DATE        NOT NULL,

    CONSTRAINT books_tb_pk_id PRIMARY KEY (id)
);
