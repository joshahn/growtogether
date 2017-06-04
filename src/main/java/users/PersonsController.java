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
        try (Connection connection = getConnection()) {
            System.out.println("Successful got a db connection");
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM person");

            List<Person> results = new ArrayList<>();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt(0));
                person.setFirstName(rs.getString(1));
                person.setLastName(rs.getString(2));
                person.setEmail(rs.getString(3));
                person.setTeam(rs.getInt(4));
                results.add(person);
            }
            return results;
        } catch (Exception e) {
            return new ArrayList<Person>();
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Person createPerson(@RequestBody Person Person) {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();

            stmt.addBatch(String.format("INSERT INTO person (id, first_name, last_name, email, team_id) VALUES (%d, %s, %s, %s, %d)",
                    Person.getId(), Person.getFirstName(), Person.getLastName(), Person.getEmail(), 1));

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
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();

            boolean result = stmt.execute(String.format("DELETE FROM person WHERE id = " + id));

            return "Person with id " + id + " deleted";
        } catch (Exception e) {
            return "Failed to delete Person with id " + id;
        }
    }
}
