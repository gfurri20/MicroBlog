/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gfurri20
 * Created: 7 mar 2020
 */
CREATE TABLE users
(
    id          INTEGER NOT NULL AUTO_INCREMENT,
    username    VARCHAR(250) NOT NULL,
    password    VARCHAR NOT NULL,
    salt        VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id                      INTEGER NOT NULL AUTO_INCREMENT,
    title                   VARCHAR(100) NOT NULL,
    content                 VARCHAR(250) NOT NULL,
    pubblication_date       VARCHAR(10) NOT NULL,
    author_id               INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);

/**CREATE TABLE comments
(
    id                      INTEGER         NOT NULL    AUTO_INCREMENT,
    content                 VARCHAR(250)    NOT NULL,
    pubblicationDate        VARCHAR(10)     NOT NULL,
    id_author               INTEGER         NOT NULL    FOREIGN KEY,
    id_correlatedPost       INTEGER         NOT NULL    FOREIGN KEY,
    PRIMARY KEY (id)
);*/

