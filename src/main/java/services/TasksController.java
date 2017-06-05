package services;

import errors.ServiceException;
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

@Controller
@Transactional
@RequestMapping("/api/tasks")
public class TasksController extends BaseController {

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody Task postTask(@RequestBody Task task, @RequestParam(value="email", required=true) String email) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            String createStatement = "INSERT INTO task (id, name, points) VALUES (?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(createStatement);
            pstmt.setInt(1, task.getId());
            pstmt.setString(2, task.getName());
            pstmt.setInt(3, task.getPoints());

            pstmt.executeUpdate();

            pstmt.close();
            connection.commit();
            connection.close();
            return task;
        } catch (Exception e) {
            throw new ServiceException("Got an error while trying to insert task");
        }
    }
}
