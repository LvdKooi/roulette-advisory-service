create table sessions (
  id int primary key AUTO_INCREMENT,
  user_id int,
  chip_value VARCHAR(50) not null,
  date_time DATE not null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into sessions values(1,2,'5', STR_TO_DATE('01-01-2019', '%d-%m-%Y'));


