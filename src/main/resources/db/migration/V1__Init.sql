CREATE EXTENSION citext;

CREATE TABLE "users"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "username"    text NOT NULL,
    "real_name" text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "vinyl"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "author"    text NOT NULL,
    "title" text NOT NULL
);