SELECT * FROM pbpag.flyway_schema_history;
create table cliente(
id bigint not null auto_increment,
nome varchar(60) not null,
email varchar(255) not null,
tefelone varchar(20) not null,
primary key(id)
);
alter table cliente add constraint uk_cliente unique(email);
