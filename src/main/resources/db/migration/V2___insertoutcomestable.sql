create table outcomes (
  id int primary key,
  session_id int ,
  outcome int,
  foreign key (session_id) references sessions(id)
);
