create table member(
  memNum int UNIQUE,
  email varchar(60) primary key,	
  name varchar(10) not null,	
  passwd varchar(12) not null,
  profile varchar(50),
  selfIntroduction varchar(200)
);

create table following(
  memberId varchar(50),
  followId varchar(50)
);

create table Rcp(
rcpNum INT NOT NULL,
title varchar(50),
foodName varchar(50),
subtitle varchar(50),
ingredient varchar(1000),
cookingTime varchar(50),
memNum int,
reg_date date,
thumbNail varchar(50),
hashTag varchar(1000),
PRIMARY KEY(rcpNum)
);

create table imgFile(
rcpNum int not null,
step int,
fileSize int,
fileName varchar(30),
content varchar(100)
);

commit;
