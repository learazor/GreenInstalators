package com.via.greeninstalators.controller;

import com.via.greeninstalators.model.user.Company;
import com.via.greeninstalators.model.user.LoginRequest;
import com.via.greeninstalators.service.AspNetIdentityPasswordVerifier;
import com.via.greeninstalators.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CompanyService companyService;

    public AuthController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<Company> company = companyService.findCompanyByEmail(loginRequest.getEmail());

        if (company.isEmpty() || !AspNetIdentityPasswordVerifier.verifyPassword(loginRequest.getPassword(), company.get().getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        session.setAttribute("companyCode", company.get().getCompanyCode());
        session.setAttribute("email", company.get().getEmail());

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
