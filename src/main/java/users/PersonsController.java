package users;

import errors.*;
import json.Person;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
@Transactional
@RequestMapping("/api/persons")
public class PersonsController extends BaseController {


    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Person getPerson(@RequestParam(value="email", required=true) String email) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT * FROM person where email = ? limit 1";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                rs.close();
                pstmt.close();
                connection.close();
                return person;
            } else {
                throw new NotFoundException("email not registered");
            }
        } catch (Exception e) {
            throw new NotFoundException("email not registered");
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Person createPerson(@RequestBody Person person) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            String createStatement = "INSERT INTO person (first_name, last_name, email) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(createStatement);
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setString(3, person.getEmail());

            pstmt.executeUpdate();

            pstmt.close();
            connection.commit();
            connection.close();
            return person;
        } catch (Exception e) {
            return person;
        }
    }

    @RequestMapping(value="{id}",method=RequestMethod.DELETE)
    public @ResponseBody String deletePerson(@PathVariable(value="id", required=true) int id) {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();

            stmt.execute(String.format("DELETE FROM person WHERE id = " + id));
            return "Person with id " + id + " deleted";
        } catch (Exception e) {
            return "Failed to delete Person with id " + id;
        }
    }
}
