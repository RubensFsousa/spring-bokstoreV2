CREATE TABLE rents_tb
(
    id             SERIAL      NOT NULL,
    created_at     TIMESTAMP   NOT NULL,
    updated_at     TIMESTAMP   NOT NULL,
    delivered_date DATE,
    deadline_date  DATE        NOT NULL,
    status         VARCHAR(25) NOT NULL,
    renter_id      SERIAL      NOT NULL,
    book_id        SERIAL      NOT NULL,

    CONSTRAINT rents_tb_pk_id PRIMARY KEY (id),
    CONSTRAINT rents_tb_renter_id_fk FOREIGN KEY (renter_id) REFERENCES renters_tb (id),
    CONSTRAINT rents_tb_book_id_fk FOREIGN KEY (book_id) REFERENCES books_tb (id)
);