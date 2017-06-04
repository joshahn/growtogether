package users;

import json.Person;
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
@RequestMapping("/persons")
public class PersonsController extends BaseController {


    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Person> getPerson(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM person");

            List<Person> results = new ArrayList<>();
            while (rs.next()) {
                Person Person = new Person();
                Person.setId(rs.getInt(0));
                Person.setFirstName(rs.getString(1));
                Person.setLastName(rs.getString(2));
                Person.setEmail(rs.getString(3));
                results.add(Person);
            }
            return results;
        } catch (Exception e) {
            return new ArrayList<Person>();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Person createPerson(@RequestBody Person Person) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            boolean result = stmt.execute(String.format("INSERT INTO person (id, first_name, last_name, email, team_id) VALUES (%d, %s, %s, %s, %d)",
                    Person.getId(), Person.getFirstName(), Person.getLastName(), Person.getEmail()), 1);

            return Person;
        } catch (Exception e) {
            return Person;
        }
    }

    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody Person updatePerson(@RequestBody Person Person) {
        return Person;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody String deletePerson(@RequestParam(value="id", required=true) int id) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            boolean result = stmt.execute(String.format("DELETE FROM Persons WHERE id = " + id));

            return "Person with id " + id + " deleted";
        } catch (Exception e) {
            return "Failed to delete Person with id " + id;
        }
    }
}
