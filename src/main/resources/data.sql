INSERT INTO users(login, password) VALUES
  ('admin', 'admin');

INSERT INTO tweets(id_creator, text) VALUES
  (1, 'Test tweet admin');

INSERT INTO comments(id_creator, id_tweet, text) VALUES
  (1, 1, 'Test comment admin');