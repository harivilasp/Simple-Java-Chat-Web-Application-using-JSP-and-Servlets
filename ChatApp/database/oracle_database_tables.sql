create table chatting (Username varchar(20) primary key, password varcahr(20));

insert into chatting values('hari', 'hari');
insert into chatting values('vilas', 'vilas');

create table chatting_data(Username varchar(20), message varchar(100), 
Posted_Date varchar(20), Posted_Timem varchar(20), sr_no int );

CREATE SEQUENCE seq_chatting_data 
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10 ;