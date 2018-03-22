CREATE TABLE IF NOT EXISTS pet (
  `id` VARCHAR(64) NOT NULL UNIQUE ,
  `date_of_birth` DATE  NULL,
  `breed` VARCHAR(100)  NULL,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (id)
);