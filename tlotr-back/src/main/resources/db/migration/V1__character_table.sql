CREATE TABLE character (
    character_id bigserial primary key,
    name varchar(100) not null,
    race varchar(50) not null
);