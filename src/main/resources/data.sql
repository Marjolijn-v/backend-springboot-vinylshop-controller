INSERT INTO publishers (id, created_date, edited_date, name, address, contact_details)
VALUES (1, now(),now(), 'The Vinyl Publisher', 'Amsterdam, NL', 'info@thevinylpublisher.nl');

INSERT INTO publishers (id, created_date, edited_date, name, address, contact_details)
VALUES (2, now(), now(), 'Retro Records', 'Rotterdam, NL', 'info@retrorecords.nl');

INSERT INTO genres (id, created_date, edited_date, name, description)
VALUES (1, now(), now(), 'Rock', 'Muziek met elektrische gitaren en drums');

INSERT INTO genres (id, created_date, edited_date, name, description)
VALUES (2, now(), now(), 'Jazz', 'Improvisatie en swingende ritmes uit New Orleans');

INSERT INTO albums (id, title, release_year, genre_id, publisher_id)
VALUES (1, 'Abbey Road', 1969, 1, 1);

INSERT INTO artists (id, name, biography, album_id)
VALUES (1, 'The Beatles', 'The Beatles were an English rock band formed in Liverpool in 1960. The band comprised John Lennon, Paul McCartney, George Harrison and Ringo Starr.', 1);

INSERT INTO stock (id, condtion, price, album_id)
VALUES (1, 'Very good', 19.95, 1);
