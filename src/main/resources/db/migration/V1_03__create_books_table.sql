CREATE TABLE books_tb
(
    id             SERIAL      NOT NULL,
    created_at     TIMESTAMP   NOT NULL,
    updated_at     TIMESTAMP   NOT NULL,
    name           VARCHAR(50) NOT NULL,
    author         VARCHAR(50) NOT NULL,
    total_quantity INTEGER     NOT NULL,
    launch_date    DATE        NOT NULL,
    publisher_id   INTEGER     NOT NULL,

    CONSTRAINT books_tb_pk_id PRIMARY KEY (id),
    CONSTRAINT books_tb_publisher_id_fk FOREIGN KEY (publisher_id) REFERENCES publishers_tb (id)
);
