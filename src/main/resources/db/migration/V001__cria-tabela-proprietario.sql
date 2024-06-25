create table proprietario(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(30) not null,
    telefone varbinary(15) not null,

    primary key (id)
);

alter table proprietario
add constraint ul_proprietario unique (email);