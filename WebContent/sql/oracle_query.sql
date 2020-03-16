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

create table order_info(
ordernum number not null primary key,
memNum int not null,
orderdate date not null,
amount number,
memName varchar(10),
address varchar(128) not null,
zipcode varchar(20) not null
);

create table order_product(
productnum int not null primary key,
ordernum number not null,
productname varchar(10),
qty int,
price number 
);

create table cart(
cartnum int not null primary key,
memNum int not null,
productname varchar(10),
qty int,
price number 
);

create table mem_address(
addressnum int not null primary key,
memNum int not null,
memName varchar(10),
address1 varchar(128) not null,
zipcode1 varchar(20) not null,
address2 varchar(128),
zipcode2 varchar(20),
address3 varchar(128),
zipcode3 varchar(20)
);

commit;
