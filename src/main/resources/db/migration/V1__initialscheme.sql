create table sessions (
  id int primary key AUTO_INCREMENT,
  user_id int,
  chip_value VARCHAR(50) not null,
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
  total_profit VARCHAR(50),
  advise_id int,
  foreign key (session_id) references sessions(id)
);

create table advises(
 id int primary key AUTO_INCREMENT,
 session_id int,
 red_advice int,
 black_advice int,
 odd_advice int,
 even_advice int,
 first_half_advice int,
 second_half_advice int,
 first_column_advice int,
 second_column_advice int,
 third_column_advice int,
 first_dozen_advice int,
 second_dozen_advice int,
 third_dozen_advice int,
 causing_outcome_id int,
  foreign key (session_id) references sessions(id),
  foreign key (causing_outcome_id) references outcomes(id)
  );

  alter table outcomes add foreign key (advise_id) references advises(id);

--ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--docker exec -it db mysql -p


