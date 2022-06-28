CREATE EXTENSION citext;

CREATE TABLE "users"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "username"    text NOT NULL,
    "real_name" text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "base"
(
    "id"       bigint PRIMARY KEY,
    "author"    text NOT NULL,
    "title" text NOT NULL
);

CREATE TABLE "vinyl"
(
    "id"       bigint PRIMARY KEY
        CONSTRAINT fk_vinyl_base_id
            REFERENCES base,
    "country_issued"    text NOT NULL,
    "price" bigint NOT NULL
);

CREATE TABLE "cd"
(
    "id"       bigint PRIMARY KEY
        CONSTRAINT fk_cd_base_id
            REFERENCES base,
    "year_issued"   integer NOT NULL
);

CREATE TABLE "book"
(
    "id"       bigint PRIMARY KEY
        CONSTRAINT fk_book_base_id
            REFERENCES base,
    "publisher"    text NOT NULL
);