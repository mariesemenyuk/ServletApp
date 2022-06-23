create table user_subscriptions (
    "user_id"     int8 not null references users,
    "vinyl_id"    int8 not null references vinyl,
    primary key (user_id, vinyl_id)
)