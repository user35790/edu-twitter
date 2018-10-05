CREATE TABLE users(
id int NOT NULL AUTO_INCREMENT,
login varchar (30) NOT NULL,
password varchar (30) NOT NULL,

name varchar (100),
date_birthday date,
sex boolean,

status varchar (50),
image varchar (300),

PRIMARY KEY (id)
);


CREATE TABLE tweets(
id int NOT NULL AUTO_INCREMENT,
id_creator int NOT NULL,

text varchar (300) NOT NULL,

date date,
likes int,

PRIMARY KEY (id)
);


CREATE TABLE comments(
id int NOT NULL AUTO_INCREMENT,
id_creator int NOT NULL,
id_tweet int NOT NULL,

text varchar (100) NOT NULL,

date date,
likes int,

PRIMARY KEY (id)
);