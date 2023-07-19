CREATE SCHEMA IF NOT EXISTS cinema_schema;
CREATE TABLE cinema_schema.users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    phone VARCHAR(20),
    passwd VARCHAR(100)
);