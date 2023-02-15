CREATE TABLE IF NOT EXISTS test
(
    id           uuid         not null primary key,
    title      varchar(255) not null,
    created_at  timestamp(6) null
    );
