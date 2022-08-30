-- public.teachers definition

-- Drop table

-- DROP TABLE public.teachers;
CREATE TABLE public.teachers (
	id serial4 NOT NULL,
	fio varchar(100) NOT NULL,
		CONSTRAINT teachers_table_pk PRIMARY KEY (id)
);