package users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Connection;
import java.sql.Statement;

@Controller
@RequestMapping("/db")
public class DBController extends BaseController{

    @RequestMapping(method= RequestMethod.GET)
    String db() {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Users (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `firstName` varchar(128) DEFAULT NULL,\n" +
                    "  `lastName` varchar(128) DEFAULT NULL,\n" +
                    "  `emailAddress` varchar(64) DEFAULT NULL,)");
            return "db initialized";
        } catch (Exception e) {
            return "error";
        }
    }
}
