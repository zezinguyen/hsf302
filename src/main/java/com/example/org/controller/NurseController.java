package com.example.org.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nurse")
@PreAuthorize("hasRole('NURSE')")
public class NurseController {

    @GetMapping("/dashboard")
    public String nurseDashboard() {
        return "nurse/dashboard";
    }
}