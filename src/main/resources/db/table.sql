drop table if exists post;

create table post(
    id bigint auto_increment,
    category varchar(2) not null,
    title varchar(30) not null,
    content text(2000),
    thumbnail mediumblob,
    user_nickname varchar(10) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    primary key (id)
);

create index post_search_idx on post (user_nickname, title);

select default_character_set_name, default_collation_name FROM information_schema.schemata
where schema_name = 'fastcampus_toy3';