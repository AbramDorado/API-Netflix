---------INITIALIZE------------------

CREATE SCHEMA streaming_service;

SET SCHEMA 'streaming_service';

CREATE TABLE user_table (
	user_id BIGSERIAL NOT NULL PRIMARY KEY,
	subscription_plan VARCHAR(255),
	available_screen INT
);

CREATE TABLE movies (
    movie_id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    duration VARCHAR(255)
);

UPDATE user_table
SET available_screen = 1
WHERE subscription_plan = 'Basic';

UPDATE user_table
SET available_screen = 2
WHERE subscription_plan = 'Standard';

UPDATE user_table
SET available_screen = 3
WHERE subscription_plan = 'Premium';

INSERT INTO user_table (user_id, subscription_plan, available_screen)
VALUES
(1, 'Basic', 1),
(2, 'Standard', 2),
(3, 'Premium', 3);

INSERT INTO movies (movie_id, title, duration)
VALUES 
(1, 'Movie 1', '1 minute'),
(2, 'Movie 2', '1 hour'),
(3, 'Movie 3', '30 seconds');


SELECT * from user_table;

--=================================================================================

---testing purposes only------

UPDATE streaming_service.user_table
SET subscription_plan = 'Basic'
WHERE user_id = 1;

UPDATE streaming_service.user_table
SET available_screen = 1
WHERE user_id = 1;

UPDATE streaming_service.user_table
SET watching_movies = null
WHERE user_id = 1;

UPDATE streaming_service.user_table
SET subscription_plan = 'Standard'
WHERE user_id = 2;

UPDATE streaming_service.user_table
SET available_screen = 2
WHERE user_id = 2;

UPDATE streaming_service.user_table
SET available_screen = 2
WHERE user_id = 5;

UPDATE streaming_service.user_table
SET watching_movies = null
WHERE user_id = 5;

----------------------------------------
UPDATE streaming_service.movies
SET duration = '2 minutes'
WHERE movie_id = 2;

UPDATE streaming_service.movies
SET duration = '1 hour'
WHERE movie_id = 3;

UPDATE streaming_service.movies
SET duration = '45 seconds'
WHERE movie_id = 5;

UPDATE streaming_service.movies
SET duration = '30 seconds'
WHERE movie_id = 1;



