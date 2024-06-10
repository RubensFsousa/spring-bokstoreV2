CREATE TABLE users_tb
(
    id         SERIAL       NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    username   VARCHAR(30)  NOT NULL,
    email      VARCHAR(30)  NOT NULL,
    "password" VARCHAR(255) NOT NULL,
    role       VARCHAR(8)   NOT NULL
);

INSERT INTO users_tb(id, email, username, password, role, created_at, updated_at)
VALUES (1, 'admin@admin.com', 'admin', '$2a$10$wGCqWXZLURgKkpyLLZCApOWml2dCV7B8sxYipV3RK.tF.erOdis2S', 'ADMIN', now(),
        now()),
       (2, 'visitor@visitor.com', 'visitor', '$2a$10$c/jO.D2yxaOwS4a9mUcv3uWyn6XlzQ11NnwNiWShxMB3Sw8ohHZoW', 'VISITOR',
        now(), now())
