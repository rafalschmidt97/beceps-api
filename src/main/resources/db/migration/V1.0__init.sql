create table user
(
    id         bigint      not null auto_increment,
    email      varchar(50) not null unique,
    password   varchar(60) not null,
    created_at datetime(6) not null default current_timestamp(6),
    is_locked  bit(1)      not null default 0,
    locked_at  datetime(6),
    primary key (id)
) engine = INNODB;

create table refresh_token
(
    id        bigint       not null auto_increment,
    issued_at datetime(6)  not null default current_timestamp(6),
    token     varchar(512) not null,
    user_id   bigint       not null,
    primary key (id),
    foreign key (user_id) references user (id) on delete cascade
) engine = INNODB;
