create table user_vinyls (
    "user_id"     int8 not null references users,
    "vinyl_id"    int8 not null references vinyl,
    primary key (user_id, vinyl_id)
);

create table user_books (
                             "user_id"     int8 not null references users,
                             "book_id"    int8 not null references book,
                             primary key (user_id, book_id)
);

create table user_cds (
                             "user_id"     int8 not null references users,
                             "cd_id"    int8 not null references cd,
                             primary key (user_id, cd_id)
)