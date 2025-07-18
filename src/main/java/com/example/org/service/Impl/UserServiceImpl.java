package com.example.org.service.Impl;

import com.example.org.entity.Role;
import com.example.org.entity.User;
import com.example.org.repository.RoleRepository;
import com.example.org.repository.UserRepository;
import com.example.org.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void updateRoles(Long userId, List<Long> roleIds) {
        User user = findById(userId);
        if (user != null) {
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
            user.setRoles(roles);
            userRepository.save(user);
        }
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
        // Không mã hóa mật khẩu
        user.setPassword(user.getPassword());
        user.setIsActive(true);

        // Đảm bảo roles không null (nếu bạn chưa khởi tạo trong constructor)
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        // Nếu không truyền role → mặc định PATIENT
        if (roleNames == null || roleNames.isEmpty()) {
            Role defaultRole = roleRepository.findByName("PATIENT");
            if (defaultRole == null) {
                throw new RuntimeException("Role 'PATIENT' not found in database.");
            }
            user.addRole(defaultRole); // Gán role theo quan hệ 2 chiều
        } else {
            for (String roleName : roleNames) {
                Role role = roleRepository.findByName(roleName);
                if (role == null) {
                    throw new RuntimeException("Role '" + roleName + "' not found.");
                }
                user.addRole(role);
            }
        }

        userRepository.save(user); // Tự động lưu vào user_roles nếu quan hệ đúng
    }



}
