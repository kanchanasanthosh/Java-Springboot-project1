User Management

create database usermanagement;

use usermanagement;

show tables

create table city_master(city_id integer not null,city_name varchar(255),state_id integer, primary key(city_id))

create table state_master(state_id integer not null,state_name varchar(255),country_id integer ,primary key(state_id))

create table country_master(country_id integer not null,country_name varchar(255),primary key(country_id))

create table User_Details (user_id integer not null auto_increment,created_date date, email varchar(255), name varchar(255), phno bigint,pwd varchar(255),
pwd_updated varchar(255),updated_date date,city_id integer,country_id integer,state_id integer,primary key (user_id)) 

drop table state_master

alter table city_master add constraint foreign key (state_id) references state_master(state_id)

alter table state_master add constraint foreign key (country_id) references country_master (country_id)

alter table user_details add constraint foreign key (city_id) references city_master(city_id)

alter table user_details add constraint foreign key (country_id) references country_master(country_id)

alter table user_details add constraint foreign key (state_id) references state_master (state_id)

insert into country_master values(1,'India');
insert into country_master values(2,'USA');

insert into state_master values(1,'Andra Pradesh',1);
insert into state_master values(2,'Tamilnadu',1);
insert into state_master values(3,'california',2);
insert into state_master values(4,'florida',2);

insert into city_master values(1,'guntoor',1);
insert into city_master values(2,'ongole',1);
insert into city_master values(3,'hydrabad',2);
insert into city_master values(4,'warangal',2);

insert into city_master values(5,'sacramento',3);
insert into city_master values(6,'fremont',3);
insert into city_master values(7,'wesley chapel',4);
insert into city_master values(8,'Tampa',4);









