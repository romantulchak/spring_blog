package com.blog.service.impl;

import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(User user) {
        User userFromBd = userRepository.findByUsername(user.getUsername());
        if (userFromBd == null) {
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }
}
