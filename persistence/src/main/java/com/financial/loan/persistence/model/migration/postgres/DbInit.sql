CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    patronymic VARCHAR(255),
    role VARCHAR(64),

    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE user_additional_data (
    id SERIAL PRIMARY KEY,
    user_id INTEGER UNIQUE,
    birthday DATE,
    passport_series VARCHAR(4),
    passport_number VARCHAR(6),
    monthly_income DOUBLE PRECISION,

    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    year INTEGER,
    cost DOUBLE PRECISION,

    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE loan_application (
    id SERIAL PRIMARY KEY,
    car_id INTEGER UNIQUE,
    user_id INTEGER,

    loan_amount DOUBLE PRECISION,
    first_payment DOUBLE PRECISION,
    term_month INTEGER,

    status VARCHAR(64),

    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    FOREIGN KEY (car_id) REFERENCES car(id),
    FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE application_history (
    id SERIAL PRIMARY KEY,
    application_id INTEGER,

    old_status VARCHAR(64),
    new_status VARCHAR(64),

    created_at TIMESTAMP,
    changed_at TIMESTAMP,

    FOREIGN KEY (application_id) REFERENCES loan_application(id)
);