CREATE TABLE users(

    id bigint NOT NULL auto_increment,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(500) NOT NULL,
    nickname VARCHAR(100) UNIQUE,
    creation_date DATETIME NOT NULL,
    active TINYINT(1) NOT NULL,

    PRIMARY KEY(id)

);
