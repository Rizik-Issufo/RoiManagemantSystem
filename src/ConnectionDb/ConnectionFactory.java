package ConnectionDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnectionTask() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Task";
        String username = "root";
        String password = "1234";
        return DriverManager.getConnection(url, username, password);
    }
public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/livraria";
        String username = "root";
        String password = "1234";
        return DriverManager.getConnection(url, username, password);
    }
}
