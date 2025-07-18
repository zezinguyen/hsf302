package com.example.org.service;

import com.example.org.entity.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByName(String name);
}