create table user
(
    id         bigint      not null auto_increment,
    email      varchar(50) not null unique,
    password   varchar(60) not null,
    created_at datetime(6) not null default current_timestamp(6),
    locked     bit(1)      not null default 0,
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

create table workout
(
    id          bigint      not null auto_increment,
    name        varchar(30) not null,
    created_at  datetime(6) not null default current_timestamp(6),
    modified_at datetime(6),
    user_id     bigint      not null,
    primary key (id),
    foreign key (user_id) references user (id) on delete cascade
) engine = INNODB;

create table routine
(
    id          bigint      not null auto_increment,
    name        varchar(30) not null,
    week_day    int         not null,
    created_at  datetime(6) not null default current_timestamp(6),
    modified_at datetime(6),
    workout_id  bigint      not null,
    primary key (id),
    foreign key (workout_id) references workout (id) on delete cascade
) engine = INNODB;

create table `set`
(
    id          bigint      not null auto_increment,
    name        varchar(30) not null,
    sets_amount int         not null,
    reps_amount int         not null,
    created_at  datetime(6) not null default current_timestamp(6),
    modified_at datetime(6),
    routine_id  bigint      not null,
    primary key (id),
    foreign key (routine_id) references routine (id) on delete cascade
) engine = INNODB;

create table exercise
(
    id         bigint      not null auto_increment,
    reps       int         not null,
    created_at datetime(6) not null default current_timestamp(6),
    set_id     bigint      not null,
    user_id    bigint      not null,
    primary key (id),
    foreign key (set_id) references `set` (id) on delete cascade,
    foreign key (user_id) references user (id) on delete cascade
) engine = INNODB;
