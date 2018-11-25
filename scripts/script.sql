CREATE SEQUENCE libro_seq;

CREATE TABLE public.libros
(
    id integer NOT NULL DEFAULT nextval('libro_seq'),
    isbn character varying(100) NOT NULL,
    nombre character varying(300) NOT NULL,
    editorial character varying(300) NOT NULL,
    autores character varying(300) NOT NULL,
    paginas integer NOT NULL DEFAULT 0,
    imagen character varying(1000),
    url character varying(1000),
    descripcion character varying(1000),
    crtd_fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    lupd_fecha TIMESTAMP,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

comment on table libros is 'Tabla destinada a contener la coleccion de libros.';
comment on column libros.id is 'ID del registro';
comment on column libros.isbn is 'Codigo del libro';
comment on column libros.nombre is 'Nombre del libro';
comment on column libros.editorial is 'Editorial del libro';
comment on column libros.autores is 'Autores del libro';
comment on column libros.paginas is 'Cantidad de paginas del libro';
comment on column libros.imagen is 'URL de la imagen del libro';
comment on column libros.url is 'URL donde adquirir el libro';
comment on column libros.descripcion is 'Descripcion del libro';
comment on column libros.crtd_fecha is 'Fecha de creacion del registro';
comment on column libros.lupd_fecha is 'Ultima fecha de actualizacion del registro';

create index on libros(id);

ALTER TABLE public.libros
    OWNER to zrigfwjkmwkurd;

ALTER SEQUENCE libro_seq
    OWNED BY libros.id;