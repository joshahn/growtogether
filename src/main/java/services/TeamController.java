package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;

import errors.ServiceException;
import json.Person;
import json.Task;
import json.Team;


@Controller
@Transactional
@RequestMapping("/api/teams")
public class TeamController extends BaseController {
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Person> getTeam(@RequestParam(value="teamId", required=true) int id) {
		System.out.println("Calling get team");
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT p.* FROM person p "
            		+ "LEFT JOIN teampersons tp ON (p.id = tp.person_id) "
            		+ "LEFT JOIN team t ON (t.id = tp.team_id) "
            		+ "WHERE t.id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<Person> persons = new ArrayList<Person>();
            while (rs.next()) {
            	Person person = new Person();
            	String email = rs.getString("email");
            	System.out.println("person's email: " + email);
            	PersonsController.setPersonAndTask(person, email);
            	System.out.println("person was added and created. " + person.toString());
                persons.add(person);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return persons;
        } catch (Exception e) {
            throw new ServiceException("Error while retrieving all the people for this team");
        }
    }
}
