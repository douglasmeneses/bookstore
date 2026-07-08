ALTER TABLE bookstore_db.book
    DROP FOREIGN KEY fk_book_publisher;

DROP TABLE bookstore_db.publisher;

CREATE TABLE bookstore_db.publisher (
   id CHAR(36) NOT NULL,
   cnpj VARCHAR(14) NOT NULL,
   name VARCHAR(120) NOT NULL,
   logo VARCHAR(255),
   about TEXT NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY uk_publisher_cnpj (cnpj)
);

ALTER TABLE bookstore_db.book
    CHANGE COLUMN publisher_cnpj publisher_id CHAR(36);

ALTER TABLE bookstore_db.book
    ADD CONSTRAINT fk_publisher_id
        FOREIGN KEY (publisher_id) REFERENCES publisher(id);