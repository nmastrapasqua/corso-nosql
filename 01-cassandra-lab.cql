# ================================================================================================
# Nome del database: nosql_course
# Nome del keyspace: tabular
# ================================================================================================

# Elenco keyspaces
DESC KEYSPACES;

# Utilizzo di un keyspaces
USE tabular;

# Creazione di una tabella
# https://cassandra.apache.org/doc/latest/cassandra/cql/types.html
CREATE TABLE students(
    id int PRIMARY KEY,
    name text,
    city text,
    fees varint,
    phone varint
);

# Verifica
DESC TABLE students;

# Inserimento
INSERT INTO students(id, name, fees)
VALUES (1, 'Joe Bloggs', 7000);

