package pl.unity.esb.gatx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    protected final static Configuration CONFIG = Configuration.getInstance();

    private Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(CONFIG.getJdbcDriver());
            return DriverManager.getConnection(CONFIG.getJdbcUrl(), CONFIG.getJdbcUserName(), CONFIG.getJdbcPassword());
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
