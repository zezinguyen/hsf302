package com.example.org.service;

import com.example.org.entity.Role;
import com.example.org.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findById(Long id);
    void updateRoles(Long userId, List<Long> roleIds);
    User findByUsername(String username);
    void saveUser(User user);
    void registerNewUser(User user, List<String> roleNames);
}