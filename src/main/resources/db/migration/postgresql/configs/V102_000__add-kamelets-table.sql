BEGIN;

CREATE TABLE kamelets
(
    id               VARCHAR(255) NOT NULL
        CONSTRAINT pk_kamelets
            PRIMARY KEY,
    name             VARCHAR(255),
    description      VARCHAR(255),
    created_when     TIMESTAMP,
    created_by_id    VARCHAR(255),
    created_by_name  VARCHAR(255),
    modified_when    TIMESTAMP,
    modified_by_id   VARCHAR(255),
    modified_by_name VARCHAR(255),
    specification TEXT
);

COMMIT;