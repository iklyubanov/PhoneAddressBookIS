-- creates the database tables

DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS address_books;

CREATE TABLE address_books
(
    id integer NOT NULL DEFAULT nextval('address_books_id_seq'::regclass),
    name character varying(255),
    CONSTRAINT address_books_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE address_books
OWNER TO postgres;

CREATE TABLE contacts
(
    id integer NOT NULL DEFAULT nextval('contacts_id_seq'::regclass),
    address_1 character varying(255),
    address_2 character varying(255),
    country character varying(255),
    county character varying(255),
    email character varying(255),
    fax character varying(100),
    first_name character varying(255),
    home_tel character varying(100),
    last_name character varying(255),
    mobile_tel character varying(100),
    post_code character varying(10),
    town character varying(255),
    work_tel character varying(100),
    addressbook_id integer NOT NULL,
    CONSTRAINT contacts_pkey PRIMARY KEY (id),
    CONSTRAINT fkc4n6kqr10nq85sjbyx1d1vio9 FOREIGN KEY (addressbook_id)
    REFERENCES address_books (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE contacts
OWNER TO postgres;

-- DROP SEQUENCE address_books_id_seq;

CREATE SEQUENCE address_books_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 4
CACHE 1;
ALTER TABLE address_books_id_seq
OWNER TO postgres;

-- DROP SEQUENCE contacts_id_seq;

CREATE SEQUENCE contacts_id_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER TABLE contacts_id_seq
OWNER TO postgres;
