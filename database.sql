drop database if exists movies;
create database if not exists movies;
use movies;

-- creates movie
drop table if exists movie;
create table if not exists movie(
	movie_id int auto_increment,
    title varchar(50) not null,
    lenght smallint not null,
    primary key (movie_id)
);

-- creates an actor
drop table if exists actor;
create table if not exists actor(
    actor_id int auto_increment,
    actor_first_name varchar(50) not null,
    actor_last_name varchar(50) not null,
    primary key (actor_id)
);

-- links actor with movie
drop table if exists movie_actor;
create table if not exists movie_actor(
	movie_id int not null,
    actor_id int not null,
    foreign key (movie_id) references movie (movie_id),
    foreign key (actor_id) references actor (actor_id)
);

-- creates genre
drop table if exists genre;
create table if not exists genre(
	genre_id int auto_increment,
	genre varchar(20) not null unique,
    primary key (genre_id)
);

-- links genre with movie
drop table if exists movie_genre;
create table if not exists movie_genre(
    movie_id int not null,
    genre_id int not null,
    foreign key (movie_id) references movie (movie_id),
    foreign key (genre_id) references genre (genre_id)
);



-- adding genres
insert into genre (genre) values ('Action');
insert into genre (genre) values ('Drama');
insert into genre (genre) values ('Horror');
insert into genre (genre) values ('Comedy');
insert into genre (genre) values ('Mystery');
insert into genre (genre) values ('Romance');
insert into genre (genre) values ('Animated');
insert into genre (genre) values ('Western');
insert into genre (genre) values ('Fantasy');
insert into genre (genre) values ('Thriller');

-- adding movies
insert into movie (title, lenght) values ('Star Wars', 135);
insert into movie (title, lenght) values ('Avatar', 116);
insert into movie (title, lenght) values ('World War C#', 120);
insert into movie (title, lenght) values ('The Shawshank Redemption', 90);
insert into movie (title, lenght) values ('The Godfather', 88);
insert into movie (title, lenght) values ('The Dark Knight', 105);
insert into movie (title, lenght) values ('The Godfather: Part II', 103);
insert into movie (title, lenght) values ('12 Angry Men ', 99);
insert into movie (title, lenght) values ('Schindlers List', 121);
insert into movie (title, lenght) values ('Pulp Fiction', 124);

-- adding genres to movies
insert into movie_genre (movie_id, genre_id) values (1, 1);
insert into movie_genre (movie_id, genre_id) values (2, 4);
insert into movie_genre (movie_id, genre_id) values (3, 5);
insert into movie_genre (movie_id, genre_id) values (4, 2);
insert into movie_genre (movie_id, genre_id) values (6, 3);
insert into movie_genre (movie_id, genre_id) values (7, 3);
insert into movie_genre (movie_id, genre_id) values (8, 3);
insert into movie_genre (movie_id, genre_id) values (9, 3);
insert into movie_genre (movie_id, genre_id) values (10, 3);



-- adding actors
insert into actor (actor_first_name, actor_last_name) VALUES ('Ismail', 'Guven');
insert into actor (actor_first_name, actor_last_name) VALUES ('Zlatan', 'Ibrahimovic');
insert into actor (actor_first_name, actor_last_name) VALUES ('Will','Smith');
insert into actor (actor_first_name, actor_last_name) VALUES ('Lara','Craft');
insert into actor (actor_first_name, actor_last_name) VALUES ('Jonny','Depp');
insert into actor (actor_first_name, actor_last_name) VALUES ('Brad','Pitt');
insert into actor (actor_first_name, actor_last_name) VALUES ('Britney','Spears');
insert into actor (actor_first_name, actor_last_name) VALUES ('Beyonce','Knowles');
insert into actor (actor_first_name, actor_last_name) VALUES ('Ursula','Magni');
insert into actor (actor_first_name, actor_last_name) VALUES ('Pagni','Kagni');


-- adding actors to movies
insert into movie_actor (movie_id, actor_id) VALUES (1, 1);
insert into movie_actor (movie_id, actor_id) VALUES (2, 1);
insert into movie_actor (movie_id, actor_id) VALUES (3, 2);
insert into movie_actor (movie_id, actor_id) VALUES (5, 10);
insert into movie_actor (movie_id, actor_id) VALUES (4, 9);
insert into movie_actor (movie_id, actor_id) VALUES (7, 8);
insert into movie_actor (movie_id, actor_id) VALUES (8, 2);
insert into movie_actor (movie_id, actor_id) VALUES (9, 3);
insert into movie_actor (movie_id, actor_id) VALUES (10, 4);
insert into movie_actor (movie_id, actor_id) VALUES (1, 6);




/*
-- SHOWS VIEW OF TITLE AND GENRE
use movies;

create or replace view titlegenre as

select movie.title, group_concat(genre.genre separator ' * ')
from movie
join movie_genre on movie.movie_id = movie_genre.movie_id
join genre on movie_genre.genre_id = genre.genre_id
group by movie.title
*/


-- SHOWS VIEW OF TITLE AND ACTOR
use movies;

create or replace view titleactor as 

select movie.title, group_concat(actor.actor_first_name,' ', actor.actor_last_name separator ' * ') as actors
from movie
join movie_actor on movie.movie_id = movie_actor.movie_id
join actor on movie_actor.actor_id = actor.actor_id
group by movie.title




