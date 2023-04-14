-- liquibase formatted sql

-- changeset garry:2
insert into pastebin (hash, body, created, expired, exposure, title)
values  ('c5de2882', 'body1', '2023-04-14 00:32:03.268023', '2026-01-08 00:32:03.268023', 0, 'title1'),
        ('f6a4dbc7', 'body2', '2023-04-14 00:32:15.966986', '2026-01-08 00:32:15.966986', 0, 'title2'),
        ('7e58f341', 'body3', '2023-04-14 00:32:28.938984', '2026-01-08 00:32:28.938984', 0, 'title3');
