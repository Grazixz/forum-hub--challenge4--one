CREATE TABLE courses(
    id BIGINT NOT NULL auto_increment,
    title VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    active TINYINT(1) NOT NULL,

    PRIMARY KEY(id)
);