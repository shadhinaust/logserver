CREATE TABLE application_log (
  id identity,
  date_time timestamp DEFAULT NOT NULL,
  process_id bigint(255) DEFAULT NOT NULL,
  level varchar(255) DEFAULT NOT NULL,
  thread_name varchar(255) DEFAULT NOT NULL,
  logger_name varchar(255) DEFAULT NOT NULL,
  message varchar(255) DEFAULT NOT NULL,
  exception text DEFAULT NULL
);