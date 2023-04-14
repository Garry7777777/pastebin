-- liquibase formatted sql

-- changeset garry:1
create table pastebin
(
    id       varchar(255) not null primary key,
    body     varchar(255),
    created  timestamp,
    expired  timestamp,
    exposure smallint,
    title    varchar(255)
);

