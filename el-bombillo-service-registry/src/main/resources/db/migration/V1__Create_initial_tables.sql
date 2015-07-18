CREATE TABLE public.service
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(64) NOT NULL,
    baseUrl VARCHAR(128) NOT NULL,
    username VARCHAR(32),
    password VARCHAR(128)
);
ALTER TABLE public.service
 ADD CONSTRAINT unique_id UNIQUE (id);
ALTER TABLE public.service
 ADD CONSTRAINT unique_name UNIQUE (name);