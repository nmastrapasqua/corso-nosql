-- ==================================================================
-- Creazione delle tabelle per 'project1'
-- SpringBoot + Cassandra
-- ==================================================================

USE chatsandra;

CREATE TABLE IF NOT EXISTS users ( 
  email       TEXT,
  name        TEXT,
  password    TEXT,
  user_id     UUID,
  PRIMARY KEY (( email ))
);


CREATE TABLE IF NOT EXISTS posts_by_user ( 
  user_id     UUID, 
  post_id     TIMEUUID,
  room_id     TEXT, 
  text        TEXT,
  PRIMARY KEY ((user_id), post_id)
) WITH CLUSTERING ORDER BY (post_id DESC);

