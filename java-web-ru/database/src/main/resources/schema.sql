-- BEGIN
CREATE TABLE IF NOT EXISTS products
(
    id
    BIGINT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    title
    VARCHAR
(
    255
) NOT NULL,
    price INT NOT NULL
    );
-- END
