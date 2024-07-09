
CREATE SEQUENCE player_id_seq;
CREATE TABLE player(
id integer NOT NULL DEFAULT nextval('player_id_seq'),
last_name character varying(50) NOT NULL,
first_name character varying(50) NOT NULL,
birth_date date NOT NULL,
points integer NOT NULL,
rank integer NOT NULL,
PRIMARY KEY (id)
);
ALTER SEQUENCE player_id_seq OWNED BY player.id;
ALTER TABLE IF EXISTS public.player OWNER to postgres;