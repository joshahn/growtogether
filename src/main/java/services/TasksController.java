package services;

import errors.NotFoundException;
import errors.ServiceException;
import json.Person;
import json.Task;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/api/tasks")
public class TasksController extends BaseController {

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody List<Task> getTasks() {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            String selectStatement = "SELECT * FROM task t";
            PreparedStatement pstmt = connection.prepareStatement(selectStatement);
            ResultSet rs = pstmt.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setPoints(rs.getInt("points"));
                tasks.add(task);
            }
            rs.close();
            pstmt.close();
            connection.close();
            return tasks;
        } catch (Exception e) {
            throw new ServiceException("Error while retrieving list of all tasks");
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Task postTask(@RequestBody Task task, @RequestParam(value="personId", required=true) int personId) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            String createStatement = "INSERT INTO persontasks (person_id, task_id, completed_date) VALUES (?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(createStatement);
            pstmt.setInt(1, personId);
            pstmt.setInt(2, task.getId());
            pstmt.setTimestamp(3, new Timestamp(task.getCompletedDate().getTime()));

            pstmt.executeUpdate();

            pstmt.close();
            connection.commit();
            connection.close();
            return task;
        } catch (Exception e) {
            throw new ServiceException("Got an error while trying to insert person task");
        }
    }
}
