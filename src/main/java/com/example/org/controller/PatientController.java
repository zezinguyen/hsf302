package com.example.org.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
@PreAuthorize("hasRole('PATIENT')")
public class PatientController {

    @GetMapping("/dashboard")
    public String patientDashboard() {
        return "patient/dashboard";
    }
}