-- CREATE TABLE IF NOT EXISTS `application_log` (
--   `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
--   `date_time` date NOT NULL,
--   `type` varchar(16) NOT NULL,
--   `level` varchar(16) NOT NULL,
--   `logger_name` varchar(255) NOT NULL,
--   `message` varchar(255) NOT NULL,
--   `exception` text DEFAULT NULL
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS server_logs (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMERY KEY,
  created_at datetime(6) DEFAULT NULL,
  created_by varchar(255) DEFAULT NULL,
  modified_at datetime(6) DEFAULT NULL,
  modified_by varchar(255) DEFAULT NULL,
  date_time datetime(6) DEFAULT NULL,
  duration bigint(20) DEFAULT NULL,
  format varchar(255) DEFAULT NULL,
  message varchar(255) DEFAULT NULL
);