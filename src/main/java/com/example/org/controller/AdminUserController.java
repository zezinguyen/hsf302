package com.example.org.controller;

import com.example.org.entity.Role;
import com.example.org.entity.User;
import com.example.org.service.RoleService;
import com.example.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "admin/users";
    }

    @PostMapping("/{userId}/roles")
    public String updateUserRoles(@PathVariable Long userId,
                                  @RequestParam List<Long> roleIds) {
        userService.updateRoles(userId, roleIds);
        return "redirect:/admin/users";
    }
}