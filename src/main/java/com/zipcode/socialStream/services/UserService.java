package com.zipcode.socialStream.services;

import com.zipcode.socialStream.dto.LoginRequest;
import com.zipcode.socialStream.models.User;
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

    public User addUser(User user) {
        User tempUser = new User();
        tempUser.setUserId(user.getUserId());
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(encodePassword(user.getPassword()));
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmailAddress(user.getEmailAddress());

        return repository.save(tempUser);
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

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public User updateUser(String username, User user){
        User ogUser = findByUsername(username);
        if(user.getFirstName() != null)
            ogUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            ogUser.setLastName(user.getLastName());
        if(user.getPassword() != null)
            ogUser.setPassword(user.getPassword());
        if(user.getEmailAddress() != null)
            ogUser.setEmailAddress(user.getEmailAddress());

        return repository.save(ogUser);
    }

    public Iterable<User> findAll(){
        return repository.findAll();
    }

    public Boolean deleteByUsername(String username){
        User user = findByUsername(username);
        if(user != null) {
            repository.delete(user);
            return true;
        }

        return false;
    }
}
