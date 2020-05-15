package com.dovetail.logserver.configuration;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class StartupDBLoad {
    @PostConstruct
    public void initDatabase() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:app_log", "sa", "");
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS `application_log` (" +
                "  `id` bigint(20) identity," +
                "  `date_time` datetime NOT NULL," +
                "  `type` varchar(16) NOT NULL," +
                "  `level` varchar(16) NOT NULL," +
                "  `logger_name` varchar(255) NOT NULL," +
                "  `message` varchar(255) NOT NULL," +
                "  `exception` text DEFAULT NULL" +
                ");";
        statement.execute(sql);
        statement.close();
        connection.close();
    }
}
