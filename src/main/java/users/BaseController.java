package users;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseController {

    protected static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String dbUser = System.getenv("JDBC_DATABASE_USERNAME");
        String dbPassword = System.getenv("JDBC_DATABASE_PASSWORD");
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
