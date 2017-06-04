package users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UsersController {


    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody User getUser(@RequestParam(value="id", required=true) int id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmail("firstlast@gpmail.org");
        return user;
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(method=RequestMethod.PUT)
    public @ResponseBody User updateUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public @ResponseBody User deleteUser(@RequestBody User user) {
        return user;
    }
}
