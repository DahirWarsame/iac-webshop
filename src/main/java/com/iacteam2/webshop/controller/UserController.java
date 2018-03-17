package com.iacteam2.webshop.controller;

import com.iacteam2.webshop.exception.InvalidCredentials;
import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.exception.UsernameAlreadyTakenException;
import com.iacteam2.webshop.model.User;
import com.iacteam2.webshop.model.UserForm;
import com.iacteam2.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private MessageDigest md5 = MessageDigest.getInstance("MD5");

    public UserController() throws NoSuchAlgorithmException {
    }

    // Get All Notes
    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId, @RequestHeader("Authorization") String token) throws UnsupportedEncodingException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }


    @PostMapping("/registration")
    public User registerUser(@Valid @
            RequestBody UserForm userForm) {

        if(userRepository.checkUserNameExistance(userForm.getUsername())) {
            throw new UsernameAlreadyTakenException("Username already exists");
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(getMD5(userForm.getPassword()));
        user.setCustomer(userForm.getCustomer());

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody UserForm userForm){
        User user = null;
        try{
            user = userRepository.getUserByUsername(userForm.getUsername());
        } catch (Exception e){
            throw new InvalidCredentials("invalid login credentials");
        }
        if (!user.getPassword().equals(getMD5(userForm.getPassword()))){
            throw new InvalidCredentials("invalid login credentials");
        }


        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                                   @Valid @RequestBody User userDetails) {

        User user= userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}