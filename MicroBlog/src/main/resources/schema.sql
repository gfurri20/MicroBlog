
/**
 * Author:  gfurri20
 * Created: 7 mar 2020
 */
CREATE TABLE posts
(
    id                      INTEGER NOT NULL AUTO_INCREMENT,
    title                   VARCHAR(100) NOT NULL,
    content                 VARCHAR(250) NOT NULL,
    pubblication_date       VARCHAR(10) NOT NULL,
    PRIMARY KEY (id),
);

/**CREATE TABLE comments
(
    id                      INTEGER         NOT NULL    AUTO_INCREMENT,
    content                 VARCHAR(250)    NOT NULL,
    pubblicationDate        VARCHAR(10)     NOT NULL,
    id_correlatedPost       INTEGER         NOT NULL    FOREIGN KEY,
    PRIMARY KEY (id)
);*/

