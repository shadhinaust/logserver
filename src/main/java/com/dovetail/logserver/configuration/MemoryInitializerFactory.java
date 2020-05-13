package com.dovetail.logserver.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class MemoryInitializerFactory {

	private static String className = "org.h2.Driver";
	private static String url = "jdbc:h2:mem:app_log";
	private static String username = "sa";
	private static String password = "";

	public static Connection getDatabaseConnection() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(className);
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS application_log (\r\n" + "  id identity,\r\n"
					+ "  date_time timestamp DEFAULT NOT NULL,\r\n" + "  level varchar(255) DEFAULT NOT NULL,\r\n"
					+ "  log_type varchar(255) DEFAULT NOT NULL,\r\n"
					+ "  logger_name varchar(255) DEFAULT NOT NULL,\r\n"
					+ "  message varchar(255) DEFAULT NOT NULL,\r\n" + "  exception text DEFAULT NULL\r\n" + ");";
			System.out.println("Created table in given database...");
			statement.close();
		} catch (Exception e) {
		}
		return connection;
	}
}
