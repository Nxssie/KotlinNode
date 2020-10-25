create database db_tasks;

use db_tasks;

create table tasks
(
    id          int auto_increment
        primary key,
    title       varchar(30)  not null,
    description varchar(255) not null,
    done        tinyint(1)   not null
);


