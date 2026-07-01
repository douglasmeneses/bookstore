CREATE TABLE IF NOT EXISTS employee (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    registration_number VARCHAR(36) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS customer (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    cpf VARCHAR(14) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS address (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    customer_id VARCHAR(36) NOT NULL,
    street VARCHAR(150) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    number VARCHAR(5) NOT NULL,
    city VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,

    CONSTRAINT fk_address_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(36) NOT NULL UNIQUE,
    total_value DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT chk_cart_total_value
        CHECK (total_value >= 0),

    CONSTRAINT fk_cart_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS publisher (
    cnpj VARCHAR(14) NOT NULL PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    logo VARCHAR(255),
    about TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS author (
    id VARCHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(120) NOT NULL,
    photo VARCHAR(255),
    biography TEXT
);

CREATE TABLE IF NOT EXISTS book (
    isbn VARCHAR(13) NOT NULL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    publisher_cnpj VARCHAR(14) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0,
    price DECIMAL(10, 2) NOT NULL,
    review DECIMAL(3, 2),
    language ENUM(
        'PORTUGUESE',
        'ENGLISH',
        'SPANISH',
        'FRENCH',
        'OTHER'
    ) NOT NULL DEFAULT 'PORTUGUESE',
    page_count INT NOT NULL,

    CONSTRAINT fk_book_publisher
        FOREIGN KEY (publisher_cnpj)
        REFERENCES publisher(cnpj)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT chk_book_price
        CHECK (price >= 0),

    CONSTRAINT chk_book_review
        CHECK (review BETWEEN 0 AND 5),

    CONSTRAINT chk_book_stock
        CHECK (stock_quantity >= 0),

    CONSTRAINT chk_book_pages
        CHECK (page_count > 0)
);

CREATE TABLE IF NOT EXISTS cart_item (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    cart_id INT NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,
    quantity INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_cart_item_cart_book
        UNIQUE (cart_id, book_isbn),

    CONSTRAINT chk_cart_item_quantity
        CHECK (quantity > 0),

    CONSTRAINT fk_cart_item_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_cart_item_book
        FOREIGN KEY (book_isbn)
        REFERENCES book(isbn)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS orders (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    cart_id INT NOT NULL,
    address_id VARCHAR(36) NOT NULL,
    customer_id VARCHAR(36) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_order_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_order_address
        FOREIGN KEY (address_id)
        REFERENCES address(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS order_item (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    order_id VARCHAR(36) NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    total_value DECIMAL(10, 2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_order_item_order_book
        UNIQUE (order_id, book_isbn),

    CONSTRAINT chk_order_item_quantity
        CHECK (quantity > 0),

    CONSTRAINT chk_order_item_unit_price
        CHECK (unit_price >= 0),

    CONSTRAINT chk_order_item_total_value
        CHECK (total_value >= 0),

    CONSTRAINT fk_order_item_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_order_item_book
        FOREIGN KEY (book_isbn)
        REFERENCES book(isbn)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS payment (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    payment_method ENUM(
        'PIX',
        'CREDIT_CARD',
        'DEBIT_CARD',
        'PAYMENT_SLIP'
    ) NOT NULL,
    status ENUM(
        'PENDING',
        'FINISHED'
    ) NOT NULL,
    total_value DECIMAL(10, 2) NOT NULL,
    order_id VARCHAR(36) NOT NULL UNIQUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT chk_payment_total_value
        CHECK (total_value >= 0),

    CONSTRAINT fk_payment_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS book_author (
    author_id VARCHAR(36) NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,

    PRIMARY KEY (author_id, book_isbn),

    CONSTRAINT fk_book_author_author
        FOREIGN KEY (author_id)
        REFERENCES author(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_book_author_book
        FOREIGN KEY (book_isbn)
        REFERENCES book(isbn)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS favorite (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    customer_id VARCHAR(36) NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uk_favorite_customer_book
        UNIQUE (customer_id, book_isbn),

    CONSTRAINT fk_favorite_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_favorite_book
        FOREIGN KEY (book_isbn)
        REFERENCES book(isbn)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS book_review (
    id VARCHAR(36) PRIMARY KEY DEFAULT (UUID()),
    customer_id VARCHAR(36) NOT NULL,
    book_isbn VARCHAR(13) NOT NULL,
    text TEXT,
    rating DECIMAL(3, 2) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT uk_book_review_customer_book
        UNIQUE (customer_id, book_isbn),

    CONSTRAINT chk_book_review_rating
        CHECK (rating BETWEEN 0 AND 5),

    CONSTRAINT fk_book_review_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_book_review_book
        FOREIGN KEY (book_isbn)
        REFERENCES book(isbn)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);