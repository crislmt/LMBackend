DROP SCHEMA store;
CREATE SCHEMA store;
use store;
CREATE TABLE user (
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(50),
                      last_name VARCHAR(50),
                      birth_date DATE,
                      email VARCHAR(90),
                      address VARCHAR(150)
);

CREATE TABLE movie (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(50),
                       release_date DATE,
                       director VARCHAR(50),
                       UNIQUE(title, release_date, director)
);

CREATE TABLE purchase (
                          id INTEGER AUTO_INCREMENT PRIMARY KEY,
                          user INTEGER,
                          purchase_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user) REFERENCES user(id)
);

CREATE TABLE movie_in_purchase (
                                   id INTEGER AUTO_INCREMENT PRIMARY KEY,
                                   purchase INTEGER,
                                   movie INTEGER,
                                   quantity INTEGER,
                                   FOREIGN KEY (purchase) REFERENCES purchase(id),
                                   FOREIGN KEY (movie) REFERENCES movie(id),
                                   UNIQUE(purchase, movie)
);

CREATE TABLE genre (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) UNIQUE
);

CREATE TABLE movie_genre(
                            id INTEGER AUTO_INCREMENT PRIMARY KEY,
                            movie INTEGER,
                            genre INTEGER,
                            FOREIGN KEY (movie) REFERENCES movie(id),
                            FOREIGN KEY (genre) REFERENCES genre(id),
                            UNIQUE(movie, genre)
);

CREATE TABLE actor(
                      id INTEGER AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(50),
                      last_name VARCHAR(50),
                      birthplace VARCHAR(50)
);

CREATE TABLE acted_in(
                         id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         actor INTEGER,
                         movie INTEGER,
                         FOREIGN KEY (actor) REFERENCES actor(id),
                         FOREIGN KEY (movie) REFERENCES movie(id),
                         UNIQUE(actor, movie)
)