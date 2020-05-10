DROP TABLE IF EXISTS application_log;

CREATE TABLE `application_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `event_date` date DEFAULT NULL,
  `exception` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `logger` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS server_logs;

CREATE TABLE `server_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL
);