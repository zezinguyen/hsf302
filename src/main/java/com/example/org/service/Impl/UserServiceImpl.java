package com.example.org.service.Impl;

import com.example.org.entity.Role;
import com.example.org.entity.User;
import com.example.org.repository.RoleRepository;
import com.example.org.repository.UserRepository;
import com.example.org.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateRoles(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsernameWithRoles(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void registerNewUser(User user, List<String> roleNames) {
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Set<Role> assignedRoles = new HashSet<>();
        if (roleNames == null || roleNames.isEmpty()) {
            Role patientRole = roleRepository.findByName("PATIENT");
            if (patientRole == null) {
                throw new RuntimeException("Default role 'PATIENT' not found in the database.");
            }
            assignedRoles.add(patientRole);
        } else {
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    throw new RuntimeException("Role '" + roleName + "' not found.");
                }
                assignedRoles.add(role);
            }
        }
        user.setRoles(assignedRoles);

        userRepository.save(user);
    }
}