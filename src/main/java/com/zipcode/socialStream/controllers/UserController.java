package com.zipcode.socialStream.controllers;

import com.zipcode.socialStream.dto.LoginRequest;
import com.zipcode.socialStream.models.User;
import com.zipcode.socialStream.services.AuthenticationResponse;
import com.zipcode.socialStream.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws Exception {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return new ResponseEntity<>(service.login(loginRequest), HttpStatus.OK);
    }




    @GetMapping("/users/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.findByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @Valid @RequestBody User user){
        return new ResponseEntity<>(service.updateUser(username, user), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Boolean> deleteUser(@RequestParam String username){
        return new ResponseEntity<>(service.deleteByUsername(username), HttpStatus.OK);
    }
}
