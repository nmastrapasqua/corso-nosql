# Creazione keyspace

CREATE KEYSPACE chatsandra WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 3};

# Mostra i keyspace presenti nel sistema
DESCRIBE KEYSPACES;


# Utilizzare un determinato keyspace
USE chatsandra;

# Creazione di una tabella
CREATE TABLE IF NOT EXISTS users ( 
  email       TEXT,
  name        TEXT,
  password    TEXT,
  user_id     UUID,
  PRIMARY KEY (( email ))
);

# Mostra le tabelle presenti nel sistema
DESC TABLES;

CREATE TABLE IF NOT EXISTS posts_by_user ( 
  user_id     UUID, 
  post_id     TIMEUUID,
  room_id     TEXT, 
  text        TEXT,
  PRIMARY KEY ((user_id), post_id)
) WITH CLUSTERING ORDER BY (post_id DESC);

CREATE TABLE IF NOT EXISTS posts_by_room ( 
  room_id     TEXT, 
  post_id     TIMEUUID,
  user_id     UUID,
  text        TEXT,
  PRIMARY KEY ((room_id), post_id)
) WITH CLUSTERING ORDER BY (post_id DESC);

