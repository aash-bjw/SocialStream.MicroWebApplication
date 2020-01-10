package com.zipcode.socialStream.services;

import com.zipcode.socialStream.dto.LoginRequest;
import com.zipcode.socialStream.models.Users;
import com.zipcode.socialStream.repositories.UserRepository;
import com.zipcode.socialStream.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public Users addUser(Users users) {
        Users tempUsers = new Users();
        tempUsers.setUserId(users.getUserId());
        tempUsers.setUsername(users.getUsername());
        tempUsers.setPassword(encodePassword(users.getPassword()));
        tempUsers.setFirstName(users.getFirstName());
        tempUsers.setLastName(users.getLastName());
        tempUsers.setEmailAddress(users.getEmailAddress());

        return repository.save(tempUsers);
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    public Users findByUsername(String username){
        return repository.findByUsername(username);
    }

    public Users updateUser(String username, Users users){
        Users ogUsers = findByUsername(username);
        if(users.getFirstName() != null)
            ogUsers.setFirstName(users.getFirstName());
        if(users.getLastName() != null)
            ogUsers.setLastName(users.getLastName());
        if(users.getPassword() != null)
            ogUsers.setPassword(users.getPassword());
        if(users.getEmailAddress() != null)
            ogUsers.setEmailAddress(users.getEmailAddress());

        return repository.save(ogUsers);
    }

    public Iterable<Users> findAll(){
        return repository.findAll();
    }

    public Boolean deleteByUsername(String username){
        Users users = findByUsername(username);
        if(users != null) {
            repository.delete(users);
            return true;
        }

        return false;
    }

    public String getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }
}
