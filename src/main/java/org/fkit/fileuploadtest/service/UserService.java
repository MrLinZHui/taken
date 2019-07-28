package org.fkit.fileuploadtest.service;

import org.fkit.fileuploadtest.domain.User;
import org.fkit.fileuploadtest.repository.UserReopsotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserReopsotory userReopsotory;
    public User findByUsername(User user){
        return userReopsotory.findByUsername(user.getUsername());
    }
    public User findUserById(String userId) {
        return userReopsotory.findUserById(userId);
    }
}