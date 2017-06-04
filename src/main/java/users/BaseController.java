package users;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseController {

    protected static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        System.out.println("JDBC_DATABASE_URL is " + dbUrl);
        System.out.println("DATABASE_URL is " + System.getenv("DATABASE_URL"));
        return DriverManager.getConnection(dbUrl);
    }
}
