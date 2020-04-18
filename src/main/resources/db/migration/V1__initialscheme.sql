create table sessions (
  id int primary key AUTO_INCREMENT,
  user_id int,
  chip_value decimal not null,
  date_time DATETIME not null
);

create table outcomes (
  id int primary key AUTO_INCREMENT,
  session_id int,
  date_time DATETIME not null,
  outcome int,
  red boolean,
  black boolean,
  odd boolean,
  even boolean,
  first_half boolean,
  second_half boolean,
  first_column boolean,
  second_column boolean,
  third_column boolean,
  first_dozen boolean,
  second_dozen boolean,
  third_dozen boolean,
  zero boolean,
  total_profit decimal,
  foreign key (session_id) references sessions(id)
);

create table advises(
 id int primary key AUTO_INCREMENT,
 session_id int,
 red_advice decimal,
 black_advice decimal,
 odd_advice decimal,
 even_advice decimal,
 first_half_advice decimal,
 second_half_advice decimal,
 first_column_advice decimal,
 second_column_advice decimal,
 third_column_advice decimal,
 first_dozen_advice decimal,
 second_dozen_advice decimal,
 third_dozen_advice decimal,
 causing_outcome_id int,
  foreign key (session_id) references sessions(id),
  foreign key (causing_outcome_id) references outcomes(id)
  );

--ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--docker exec -it db mysql -p


