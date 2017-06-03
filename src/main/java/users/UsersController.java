package users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController{


    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<User> getUser(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + id);

            List<User> results = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(0));
                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setEmail(rs.getString(3));
                results.add(user);
            }
            return results;
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            boolean result = stmt.execute(String.format("INSERT INTO users (id, firstName, lastName, email) VALUES (%d, %s, %s, %s)",
                    user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));

            return user;
        } catch (Exception e) {
            return user;
        }
    }

    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody User updateUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            boolean result = stmt.execute(String.format("DELETE FROM users WHERE id = " + id));

            return "User with id " + id + " deleted";
        } catch (Exception e) {
            return "Failed to delete user with id " + id;
        }
    }
}
