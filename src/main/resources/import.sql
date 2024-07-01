INSERT INTO MOVIES (title, release_year) VALUES ('La La Land', 2016);
INSERT INTO MOVIES (title, release_year) VALUES ('Golden Eye', 1999);
INSERT INTO MOVIES (title, release_year) VALUES ('Interstellar', 2016);
INSERT INTO MOVIES (title, release_year) VALUES ('Nice Guys', 2020);

INSERT INTO ACTORS (name) values ('Emma Stone');
INSERT INTO ACTORS (name) values ('Ryan Gosling');
INSERT INTO ACTORS (name) values ('Pierce Brosnan');
INSERT INTO ACTORS (name) values ('Russel Crowe');

INSERT INTO MOVIES_ACTORS (actors_id, movie_id) values (1, 1);
INSERT INTO MOVIES_ACTORS (actors_Id, movie_Id) values (2, 1);
INSERT INTO MOVIES_ACTORS (actors_Id, movie_Id) values (3, 2);
INSERT INTO MOVIES_ACTORS (actors_Id, movie_Id) values (2, 4);
INSERT INTO MOVIES_ACTORS (actors_Id, movie_Id) values (4, 4);