package com.iacteam2.webshop.repository;

import com.iacteam2.webshop.exception.UserNotFoundException;
import com.iacteam2.webshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkUserNameExistance(String username){
        List<User> allUsers = userRepository.findAll();
        for (User u : allUsers){
            if (username.equals(u.getUsername())) {
                return true;
            }
        } return false;
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers){
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        throw new UserNotFoundException("unknown username");
    }
}
