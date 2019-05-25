create table sessions (
  id int primary key AUTO_INCREMENT,
  user_id int,
  chip_value VARCHAR(50) not null,
  date_time DATETIME not null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--docker exec -it db mysql -p


