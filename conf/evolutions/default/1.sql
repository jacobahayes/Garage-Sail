# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table login_user (
  username                  varchar(255),
  password                  varchar(255))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists login_user;

SET REFERENTIAL_INTEGRITY TRUE;

