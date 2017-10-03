package com.thota.mockito.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.thota.mockito.domain.User;
import com.thota.mockito.repository.UserRepository;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {this.userRepository = userRepository;}

    public User getUserByLastName(final String lastName) {
        for (final User user : userRepository.findAll()) {
            if (user.getLastName().equals(lastName) ) {
                return user;
            }
        }
        return null;
    }

    public Iterable<User> getAllUsersOrderByLastName() {
    	
    List<User> userList = new ArrayList<User>();
    
    userList = (List<User>) userRepository.findAll();
    
    Collections.sort(userList);
   
    
    return userList;
    
        
    }
}
