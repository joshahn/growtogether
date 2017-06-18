package services;

import errors.*;
import json.Person;
import json.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/api/persons")
public class PersonsController extends BaseController {

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Person getPerson(@RequestParam(value="email", required=true) String email) {
        
    	Person person = new Person();
    	setPersonAndTask(person, email);
	    setTeam(person, email);
	    return person;
	    }
    
    protected static void setPersonAndTask(Person person, String email) {
    	try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT p.id as person_id, p.first_name, p.last_name, p.email, "
            		+ "t.id as team_id, t.name, t.points, pt.completed_date "
            		+ "FROM person p LEFT JOIN persontasks pt ON (p.id = pt.person_id) LEFT JOIN task t "
            		+ "ON  (t.id = pt.task_id) WHERE p.email = ?;";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person.setId(rs.getInt("person_id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                List<Task> tasks = new ArrayList<>();
                Task task = new Task();
                Integer totalPoints = 0;
                String taskName = rs.getString("name");
                System.out.println("Task name is: " + rs.getString("name"));
                if (taskName != null) {
                	task.setName(taskName);
                	task.setName(rs.getString("name"));
                    task.setPoints(rs.getInt("points"));
                    tasks.add(task);
                    totalPoints += task.getPoints();
                    while (rs.next()) {
                        task = new Task();
                        task.setId(rs.getInt("team_id"));
                        task.setName(rs.getString("name"));
                        task.setPoints(rs.getInt("points"));
                        Timestamp time = rs.getTimestamp("completed_date");
                        Long timeInMilliSeconds = time.getTime() / 1000;
                        Integer timeAsInt = Integer.valueOf(timeInMilliSeconds.intValue());
                        task.setCompletedDate(timeAsInt);
                        totalPoints += task.getPoints();
                        tasks.add(task);
                    }
                }
                person.setTasks(tasks);
                person.setTotalPoints(totalPoints);
                rs.close();
                pstmt.close();
                connection.close();
            } else {
                throw new NotFoundException("email not registered");
            }
    	} catch (Exception e) {
            throw new NotFoundException("email not registered");
        }
    }
    
    private static void setTeam(Person person, String email) {
    	try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT t.id FROM person p "
            		+ "LEFT JOIN teampersons tp ON (p.id = tp.person_id) "
            		+ "LEFT JOIN team t ON (t.id = tp.team_id) "
            		+ "WHERE p.email = ?;";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	person.setTeamId(rs.getInt("id"));
            }
            rs.close();
            pstmt.close();
            connection.close();
    	}
    	catch (Exception e) {
            throw new NotFoundException("email not registered");
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Person createPerson(@RequestBody Person person) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            String createStatement = "INSERT INTO person (first_name, last_name, email) VALUES (?, ?, ?);";
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
            throw new ServiceException("Got an error while trying to create person with email " + person.getEmail());
        }
    }
}
