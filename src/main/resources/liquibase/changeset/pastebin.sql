-- liquibase formatted sql

-- changeset garry:1
create table pastebin
(
    id       bigserial not null primary key ,
    hash     varchar(255),
    body     varchar(255),
    created  timestamp,
    expired  timestamp,
    exposure smallint,
    title    varchar(255)
);

