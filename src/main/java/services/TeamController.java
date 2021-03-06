package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/{teamId}", method=RequestMethod.GET)
    public @ResponseBody List<Person> getTeam(@PathVariable("teamId") int teamId) {
		return getTeamById(teamId);
    }
	
	private List<Person> getTeamById(int teamId) {
		System.out.println("Calling get team");
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT p.* FROM person p "
            		+ "LEFT JOIN teampersons tp ON (p.id = tp.person_id) "
            		+ "LEFT JOIN team t ON (t.id = tp.team_id) "
            		+ "WHERE t.id = ?;";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            pstmt.setInt(1, teamId);
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

	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Team> getTeams() {
		try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT * from team;";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            ResultSet rs = pstmt.executeQuery();
            List<Team> teams = new ArrayList<Team>();
            while (rs.next()) {
            	Team team = new Team();
            	int teamId = rs.getInt("id");
            	team.setId(teamId);
            	team.setName(rs.getString("name"));
            	List<Person> persons = getTeamById(teamId);
            	int totalPoints = 0;
            	for (Person person: persons) {
            		totalPoints += person.getTotalPoints();
            	}
            	team.setTotalPoints(totalPoints);
            	teams.add(team);
            }
            return teams;
		} catch (Exception e) {
            throw new ServiceException("Error while retrieving all the teams");
        }
	}
}
