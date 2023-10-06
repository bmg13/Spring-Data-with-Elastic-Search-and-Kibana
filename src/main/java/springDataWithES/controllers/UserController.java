package springDataWithES.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springDataWithES.models.DTO.Author;
import springDataWithES.models.DTO.User;
import springDataWithES.services.UserService;
import java.util.List;

@RestController
@RequestMapping(
        path = "/",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "user")
    public User retrieveUser(@PathVariable String name) {
        return this.userService.retrieveUserByName(name);
    }

    @GetMapping(path = "all/user")
    public List<User> retrieveAllUsers() {
        return this.userService.retrieveAllUsers();
    }

    @PostMapping(path = "user/add")
    public void addUser(@RequestBody User user) {
        this.userService.addUser(user);
    }


    @PostMapping(path = "director/add")
    public void addDirector(@RequestBody Author director) {
        this.userService.addUser(director);
    }
}