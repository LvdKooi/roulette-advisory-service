create table sessions (
  id int primary key,
  user_id int,
  chip_value VARCHAR(50) not null,
  date_time DATE not null
);


