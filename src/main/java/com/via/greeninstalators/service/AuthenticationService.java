package com.via.greeninstalators.service;

import com.via.greeninstalators.model.user.Company;
import com.via.greeninstalators.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final CompanyRepository companyRepository;

    @Autowired
    public AuthenticationService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public boolean authenticate(String email, String password) {
        Optional<Company> company = companyRepository.findByEmail(email);
        if (company.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(password, company.get().getPasswordHash());
    }
}
