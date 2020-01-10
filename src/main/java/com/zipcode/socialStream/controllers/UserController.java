package com.zipcode.socialStream.controllers;

import com.zipcode.socialStream.dto.LoginRequest;
import com.zipcode.socialStream.models.Users;
import com.zipcode.socialStream.services.AuthenticationResponse;
import com.zipcode.socialStream.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200", "https://cfapps.io", "https://social-stream-ui.cfapps.io", "https://social-stream-app.cfapps.io" })
@Controller
public class UserController {

    @Autowired
    private UserService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users users) {
        String username = users.getUsername();
        LOGGER.info("User signup request received: {}", username);
        return new ResponseEntity<>(service.addUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(service.login(loginRequest), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<Users>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }



    @GetMapping("/users/{username}")
    public ResponseEntity<Users> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/users/current")
    public ResponseEntity<String> getUser(){
        return new ResponseEntity<>(service.getCurrentUser(), HttpStatus.OK);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<Users> updateUser(@PathVariable String username, @Valid @RequestBody Users users){
        return new ResponseEntity<>(service.updateUser(username, users), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Boolean> deleteUser(@RequestParam String username){
        return new ResponseEntity<>(service.deleteByUsername(username), HttpStatus.OK);
    }
}
