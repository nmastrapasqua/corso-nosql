-- ================================================================================================
-- Creare un account free su https://astra.datastax.com
-- Creare il database nosql_course e keyspace tabular
-- ================================================================================================

-- Elenco keyspaces
-- ========================
DESC KEYSPACES;

-- Utilizzo di un keyspaces
-- ========================
USE tabular;

-- Creazione di una tabella
-- https://cassandra.apache.org/doc/latest/cassandra/cql/types.html
-- ========================
CREATE TABLE students(
    id int PRIMARY KEY,
    name text,
    city text,
    fees varint,
    phone varint
);

-- Verifica
-- ========================
DESC TABLE students;

-- Inserimento
-- ========================
INSERT INTO students(id, name, fees)
VALUES (1, 'Joe Bloggs', 7000);

-- Inserire altre righe, ad esempio
-- 2, Jane Doe, 5500
-- 3, Jonny Cash, 8000
-- https://7esl.com/english-names/

-- Lettura
-- ========================
SELECT * FROM students;
SELECT id, name FROM students;
SELECT * FROM students WHERE id=2;

-- Aggiornamento
-- ========================
UPDATE students SET fees = 5000 WHERE id=2;

-- Cancellazione
-- ========================
DELETE FROM students WHERE id=3;

-- Batch
-- ========================
BEGIN BATCH
INSERT INTO students(id, fees, name) VALUES (4, 3500, 'Tony Montana');
UPDATE students SET fees=6000 WHERE id=1;
DELETE fees FROM students WHERE id=3;
APPLY BATCH;

-- Cosa succede se una operazione nel batch fallisce?

-- Modifica tabella
-- ========================
ALTER TABLE students ADD email text;
ALTER TABLE students DROP email;

-- Utilizzo indici secondari
-- ========================

-- Cosa succede se nella clausola WHERE utilizziamo un campo
-- diverso da 'id'?

CREATE INDEX idx_name ON students(name);

-- Cancellazione di una tabella
-- ========================
DROP TABLE students;

-- Collezioni: set
-- ========================
CREATE TABLE staff(
    id int PRIMARY KEY,
    first_name text,
    last_name text,
    email set<text>
);

INSERT INTO staff (id, first_name, last_name, email)
VALUES (1, 'Joe', 'Bloggs', {'joe@bloggs.com'});

UPDATE staff SET email = email + {'joe@showmethemoney.com'} WHERE id=1;

-- Cosa succede se aggiungiamo a 'email' un valore che già esiste?

-- Collezioni: list
-- ========================
CREATE TABLE upcoming_calendar ( 
    year int, 
    month int, 
    events list<text>, 
    PRIMARY KEY ( year, month) 
);

INSERT INTO upcoming_calendar(year, month, events)
VALUES(2023, 9, ['Corso NoSQL', 'Corso Java']);

INSERT INTO upcoming_calendar(year, month, events)
VALUES(2023, 10, ['Corso SpringBoot']);

-- Cosa succede se aggiungiamo a 'events' un valore che già esiste?
-- Sapresti descrivere il ruolo del campo 'month'?

-- Collezioni: map
-- ========================
CREATE TABLE food_menu (
    order_id int primary key,
    order_date date,
    order_cost int,    
    menu_items map<text, text>
);

INSERT INTO food_menu (order_id, order_date, order_cost, menu_items)
VALUES (
    1, 
    '2019-02-13', 
    800, 
    {
        'Antipasto' : 'Tagliere Affettati misti',  
        'Primo' : 'Tagliatelle al Ragù',
        'Secondo' : 'Coniglio in Porchetta'
    }
);

INSERT INTO food_menu (order_id, order_date, order_cost, menu_items)
VALUES (
    2, 
    '2019-04-23', 
    200, 
    {
        'Antipasto' : 'Crostini',  
        'Primo' : 'Strozzapreti ai Pinoli',
        'Secondo' : 'Grigliata mista di carne'
    }
);
