CREATE SCHEMA IF NOT EXISTS cinema_schema;
CREATE TABLE IF NOT EXISTS cinema_schema.users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    phone VARCHAR(20),
    passwd VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS cinema_schema.users_logging(
    id SERIAL PRIMARY KEY,
    userId SERIAL,
    timestamp TIMESTAMP,
    ip VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS cinema_schema.user_images(
    id SERIAL PRIMARY KEY,
    userId SERIAL,
    fileName VARCHAR(250),
    fileSize NUMERIC,
    mime VARCHAR(150)
);