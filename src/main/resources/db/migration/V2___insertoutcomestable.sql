create table outcomes (
  id int primary key AUTO_INCREMENT,
  session_id int ,
  outcome int,
  foreign key (session_id) references sessions(id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
