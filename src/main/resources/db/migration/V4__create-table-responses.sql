CREATE TABLE responses(

    id BIGINT NOT NULL auto_increment,
    message VARCHAR(1000),
    id_user BIGINT,
    id_topic BIGINT,
    creation_date DATETIME NOT NULL,
    CONSTRAINT fk_user_response FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_topic_response FOREIGN KEY (id_topic) REFERENCES topics(id),

    PRIMARY KEY(id)

);