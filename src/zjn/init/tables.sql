CREATE TABLE movies (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    type varchar(100) default NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    sex varchar(4) default NULL,
    hobby varchar(100) default NULL,
    PRIMARY KEY (id) 
);

CREATE TABLE prefile (
    movieID  INTEGER NOT NULL AUTO_INCREMENT,
    sex varchar(4) default NULL,
    PRIMARY KEY (movieID) 
);

CREATE TABLE movie_preferences (
    userID INTEGER NOT NULL,
    movieID INTEGER NOT NULL,
    preference INTEGER NOT NULL DEFAULT 0, 
    FOREIGN KEY (userID) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (movieID) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE TABLE movie_similarity (
    movieID1 INTEGER NOT NULL,
    movieID2 INTEGER NOT NULL,
    similarity DOUBLE NOT NULL DEFAULT 0, 
    FOREIGN KEY (movieID1) REFERENCES movies(id) ON DELETE CASCADE,
    FOREIGN KEY (movieID2) REFERENCES movies(id) ON DELETE CASCADE
);

CREATE INDEX movie_preferences_index1 ON movie_preferences ( userID , movieID );
CREATE INDEX movie_preferences_index2 ON movie_preferences ( userID );
CREATE INDEX movie_preferences_index3 ON movie_preferences ( movieID );
