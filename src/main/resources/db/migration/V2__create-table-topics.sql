CREATE TABLE topics(

    id BIGINT NOT NULL auto_increment,
    title VARCHAR(100) UNIQUE,
    message VARCHAR(1000),
    id_user BIGINT,
    coursework VARCHAR(300) NOT NULL,
    creation_date datetime NOT NULL,
    resolved TINYINT(1) NOT NULL,
    CONSTRAINT fk_user_topic FOREIGN KEY (id_user) REFERENCES users(id),

    PRIMARY KEY(id)

);