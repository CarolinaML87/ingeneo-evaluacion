CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

CREATE TABLE IF NOT EXISTS public.ct_customers
(
    id integer NOT NULL DEFAULT nextval('ct_customers_id_seq'::regclass),
    first_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    address character varying(100) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(8) COLLATE pg_catalog."default" NOT NULL,
    status boolean NOT NULL DEFAULT true,
    fh_created date NOT NULL,
    email character varying(75) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ct_customers_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.ct_destinations
(
    id integer NOT NULL DEFAULT nextval('ct_destinations_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    type "char" NOT NULL,
    status boolean NOT NULL,
    address character varying(250) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ct_destinations_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.ct_products
(
    id integer NOT NULL DEFAULT nextval('ct_products_id_seq'::regclass),
    name character varying(75) COLLATE pg_catalog."default" NOT NULL,
    description character varying(125) COLLATE pg_catalog."default" NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT ct_products_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.ct_roles
(
    id integer NOT NULL DEFAULT nextval('ct_roles_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    description character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ct_roles_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.ct_users
(
    id integer NOT NULL DEFAULT nextval('ct_users_id_seq'::regclass),
    user_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    fh_created date NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ct_users_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.ct_user_rol
(
    id integer NOT NULL DEFAULT nextval('ct_user_rol_id_seq'::regclass),
    id_user integer NOT NULL,
    id_rol integer NOT NULL,
    CONSTRAINT ct_user_rol_pkey PRIMARY KEY (id_user),
    CONSTRAINT fk_rol FOREIGN KEY (id_rol)
        REFERENCES public.ct_roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_user FOREIGN KEY (id_user)
        REFERENCES public.ct_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

CREATE TABLE IF NOT EXISTS public.tt_deliveries
(
    id integer NOT NULL DEFAULT nextval('tt_deliveries_id_seq'::regclass),
    id_customer integer NOT NULL,
    id_destination integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer NOT NULL,
    amount double precision NOT NULL,
    disscount double precision NOT NULL,
    fh_created date NOT NULL,
    fh_delivered date NOT NULL,
    transport_number character varying(8) COLLATE pg_catalog."default" NOT NULL,
    traking_number character varying(10) COLLATE pg_catalog."default" NOT NULL,
    status boolean,
    CONSTRAINT tt_deliveries_pkey PRIMARY KEY (id_customer),
    CONSTRAINT fk_customer FOREIGN KEY (id_customer)
        REFERENCES public.ct_customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_destination FOREIGN KEY (id_destination)
        REFERENCES public.ct_destinations (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_product FOREIGN KEY (id_product)
        REFERENCES public.ct_products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

CREATE SEQUENCE IF NOT EXISTS public.ct_customers_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_customers.id;

CREATE SEQUENCE IF NOT EXISTS public.ct_destinations_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_destinations.id;

CREATE SEQUENCE IF NOT EXISTS public.ct_products_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_products.id;

CREATE SEQUENCE IF NOT EXISTS public.ct_roles_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_roles.id;

CREATE SEQUENCE IF NOT EXISTS public.ct_user_rol_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_user_rol.id;

CREATE SEQUENCE IF NOT EXISTS public.ct_users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY ct_users.id;

CREATE SEQUENCE IF NOT EXISTS public.tt_deliveries_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY tt_deliveries.id;


INSERT INTO public.ct_roles(
	id, name, description)
	VALUES (1, "ADMIN", "Usuario administrador del sistema privilegios ilimitados");


INSERT INTO public.ct_roles(
	id, name, description)
	VALUES (2, "USER", "Usuario habilitado para crear deliveries");