create table account
(
    id         bigint      not null auto_increment,
    email      varchar(50) not null unique,
    password   varchar(60) not null,
    created_at datetime(6) not null default current_timestamp(6),
    primary key (id)
) engine = INNODB;

create table refresh_token
(
    id         bigint       not null auto_increment,
    issued_at  datetime(6)  not null default current_timestamp(6),
    token      varchar(512) not null,
    account_id bigint       not null,
    primary key (id),
    foreign key (account_id) references account (id) on delete cascade
) engine = INNODB;
