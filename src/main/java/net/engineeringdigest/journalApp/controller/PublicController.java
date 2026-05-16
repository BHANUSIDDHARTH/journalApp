package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "healthcheck";
    }
    @PostMapping("/createuser")
    public void createUser(@RequestBody Users user) {
        userService.saveNewUser(user);
    }



}
