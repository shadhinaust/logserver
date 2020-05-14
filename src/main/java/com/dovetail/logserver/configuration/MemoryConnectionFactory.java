package com.dovetail.logserver.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class MemoryConnectionFactory {
	private static BasicDataSource dataSource;
	private static Connection connection;

	private static String className = "org.h2.Driver";
	private static String url = "jdbc:h2:mem:app_log";
	private static String username = "sa";
	private static String password = "";

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			System.out.println("Preparing memory...");
			dataSource = new BasicDataSource();
			dataSource.setUrl(url);
			dataSource.setDriverClassName(className);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			/*
			 * Statement statement = connection.createStatement(); String sql =
			 * "CREATE TABLE IF NOT EXISTS application_log (\r\n" + "  id identity,\r\n" +
			 * "  date_time timestamp DEFAULT NOT NULL,\r\n" +
			 * "  level varchar(255) DEFAULT NOT NULL,\r\n" +
			 * "  log_type varchar(255) DEFAULT NOT NULL,\r\n" +
			 * "  logger_name varchar(255) DEFAULT NOT NULL,\r\n" +
			 * "  message varchar(255) DEFAULT NOT NULL,\r\n" +
			 * "  exception text DEFAULT NULL\r\n" + ");";
			 * System.out.println("Created table in given database..."); statement.close();
			 */
			connection = dataSource.getConnection();
		}
		return connection;
	}
}
