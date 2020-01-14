create table account
(
    id           bigint      not null auto_increment,
    email        varchar(50) not null unique,
    password     varchar(60) not null,
    created_at   datetime(6) not null default current_timestamp(6),
    primary key (id)
) engine = INNODB;
