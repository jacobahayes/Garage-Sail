# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table login_user (
  username                  varchar(255),
  password                  varchar(255))
;

create table person (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create sequence person_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists login_user;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists person_seq;

