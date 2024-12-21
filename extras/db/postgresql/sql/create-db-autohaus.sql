CREATE ROLE autohaus LOGIN PASSWORD 'p';

CREATE DATABASE autohaus;

GRANT ALL ON DATABASE autohaus TO autohaus;

CREATE TABLESPACE autohausspace OWNER autohaus LOCATION '/var/lib/postgresql/tablespace/autohaus';
