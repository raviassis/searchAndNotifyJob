package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                var url = System.getenv("search_and_notify_db_url");
                var user = System.getenv("search_and_notify_db_user");
                var password = System.getenv("search_and_notify_db_password");
                connection = DriverManager.getConnection(
                    url,
                    user,
                    password
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
