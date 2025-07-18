package com.example.org.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String redirectBasedOnRole(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            var authorities = authentication.getAuthorities();

            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/dashboard";
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
                return "redirect:/doctor/dashboard";
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_NURSE"))) {
                return "redirect:/nurse/dashboard";
            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"))) {
                return "redirect:/patient/dashboard";
            }
        }
        return "redirect:/login?error=unauthorized";
    }
}

