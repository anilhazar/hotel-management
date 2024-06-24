package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection = null;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private static final String DBNAME = "database";

    private static final String HOST = "localhost";

    private static final int PORT = 9999;

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                final String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;
                connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return connection;
    }
}

