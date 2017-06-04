package users;

import json.Person;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/persons")
public class PersonsController extends BaseController {


    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Person> getPerson(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");

            List<Person> results = new ArrayList<>();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                person.setTeamId(rs.getInt("team_id"));
                results.add(person);
            }
            return results;
        } catch (Exception e) {
            return new ArrayList<Person>();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Person createPerson(@RequestBody Person person) {
        try (Connection connection = getConnection()) {
            System.out.println("Successful got a db connection");
            connection.setAutoCommit(false);

            String createStatement = "INSERT INTO person (first_name, last_name, email, team_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(createStatement);
            System.out.println("created prepare statement");
            pstmt.setString(1, person.getFirstName());
            System.out.println("set first name");
            pstmt.setString(2, person.getLastName());
            System.out.println("set last name");
            pstmt.setString(3, person.getEmail());
            System.out.println("set email");
            pstmt.setInt(4, person.getTeamId());
            System.out.println("set team id");

            int affectedRows = pstmt.executeUpdate();

            System.out.println("Successfully executed createPerson query for affectedRows " + affectedRows);

            pstmt.close();
            connection.commit();
            connection.close();
            return person;
        } catch (Exception e) {
            return person;
        }
    }

    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody Person updatePerson(@RequestBody Person person) {
        return person;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody String deletePerson(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = getConnection()) {
            System.out.println("Successful got a db connection");
            Statement stmt = connection.createStatement();

            stmt.execute(String.format("DELETE FROM person WHERE id = " + id));
            System.out.println("Successfully executed deletePerson query");

            return "Person with id " + id + " deleted";
        } catch (Exception e) {
            return "Failed to delete Person with id " + id;
        }
    }
}
