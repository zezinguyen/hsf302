package com.example.org.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = "/login?error=unauthorized"; // Default redirect

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/dashboard";
                break;
            } else if (authorityName.equals("ROLE_DOCTOR")) {
                redirectUrl = "/doctor/dashboard";
                break;
            } else if (authorityName.equals("ROLE_NURSE")) {
                redirectUrl = "/nurse/dashboard";
                break;
            } else if (authorityName.equals("ROLE_PATIENT")) {
                redirectUrl = "/patient/dashboard";
                break;
            }
        }

        if (response.isCommitted()) {
            return;
        }

        response.sendRedirect(redirectUrl);
    }
}