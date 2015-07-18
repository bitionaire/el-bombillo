CREATE TABLE public.user
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(64) NOT NULL,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL
);
ALTER TABLE public.user
 ADD CONSTRAINT unique_id UNIQUE (id);
ALTER TABLE public.user
 ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE public.user
 ADD CONSTRAINT unique_email UNIQUE (email);