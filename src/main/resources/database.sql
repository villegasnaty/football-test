CREATE DATABASE footballdemo;
use footballdemo;

create table competition (
   id bigint not null,
    area_name varchar(255),
    code varchar(255) not null,
    competition_id bigint not null,
    name varchar(255),
    primary key (id)
)

create table competition_team_dtos (
   competition_id bigint not null,
    team_dtos_id bigint not null,
    primary key (competition_id, team_dtos_id)
)

create table hibernate_sequence (
   next_val bigint
)

insert into hibernate_sequence values ( 1 )

insert into hibernate_sequence values ( 1 )

insert into hibernate_sequence values ( 1 )

create table player (
   id bigint not null,
    country_of_birth varchar(255),
    date_of_birth varchar(255),
    name varchar(255),
    nationality varchar(255),
    position varchar(255),
    team_id bigint not null,
    primary key (id)
)

create table team (
   id bigint not null,
    area_name varchar(255),
    email varchar(255),
    name varchar(255),
    short_name varchar(255),
    team_id bigint not null,
    tla varchar(255),
    competition_id bigint not null,
    primary key (id)
)

create table team_players (
   team_id bigint not null,
    players_id bigint not null,
    primary key (team_id, players_id)
)

alter table competition
   add constraint UK_snapb7ntg2hxfov28cgeumb92 unique (competition_id)

alter table competition
   add constraint UK_crgma3kwhn5c9j7pk5txbeh37 unique (code)

alter table competition_team_dtos
   add constraint UK_rtms3m6b50sai0v5sv66iutpj unique (team_dtos_id)

alter table team
   add constraint UK_halwywd1l5mvk0fsqchh2o3e0 unique (team_id)

alter table team_players
   add constraint UK_j7gm7vpm2kn3c07shxqlvt4al unique (players_id)

alter table competition_team_dtos
   add constraint FK1kscfrpgv2kodjngwaxi52qmq
   foreign key (team_dtos_id)
   references team (id)

alter table competition_team_dtos
   add constraint FK7opjj939i5cvop8kxpxav4lbq
   foreign key (competition_id)
   references competition (id)

alter table player
   add constraint FKdvd6ljes11r44igawmpm1mc5s
   foreign key (team_id)
   references team (id)

alter table team
   add constraint FKqhhapgh63c9yjo4tc0uf6ynt1
   foreign key (competition_id)
   references competition (competition_id)

alter table team_players
   add constraint FKoh3nyypbfjy208372s2aq12ei
   foreign key (players_id)
   references player (id)

alter table team_players
   add constraint FKc9igy2kys82rwa80px3q0usqa
   foreign key (team_id)
   references team (id)
