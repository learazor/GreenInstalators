package com.via.greeninstalators.service;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.model.user.Company;
import com.via.greeninstalators.repository.CompanyInfoRepository;
import com.via.greeninstalators.repository.CompanyInstallationRepository;
import com.via.greeninstalators.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyInstallationRepository installationRepository;
    private final CompanyRepository companyRepository;


    public CompanyService(CompanyInfoRepository companyInfoRepository, CompanyInstallationRepository installationRepository,
                          CompanyRepository companyRepository) {
        this.companyInfoRepository = companyInfoRepository;
        this.installationRepository = installationRepository;
        this.companyRepository = companyRepository;
    }

    //Saves company information.
    public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
        if (companyInfo == null) {
            throw new IllegalArgumentException("CompanyInfo cannot be null.");
        }
        return companyInfoRepository.save(companyInfo);
    }

    public CompanyInstallation saveCompanyInstallation(CompanyInstallation installation) {
        if (installation == null || installation.getCompanyCode() == null) {
            throw new IllegalArgumentException("Installation or company code cannot be null.");
        }
        // Validate if the company exists
        Optional<CompanyInfo> companyInfoOptional = companyInfoRepository.findByCompanyCode(installation.getCompanyCode());
        if (companyInfoOptional.isEmpty()) {
            throw new IllegalArgumentException("Company with code " + installation.getCompanyCode() + " does not exist.");
        }

        // Save the installation
        return installationRepository.save(installation);
    }

    //Retrieves company information by company code
    public Optional<CompanyInfo> getCompanyInfoByCode(String companyCode) {
        if (companyCode == null || companyCode.isBlank()) {
            throw new IllegalArgumentException("Company code cannot be null or empty.");
        }
        return companyInfoRepository.findByCompanyCode(companyCode);
    }

    //Retrieves installations by company code.
    public List<CompanyInstallation> getInstallationsByCompanyCode(String companyCode) {
        if (companyCode == null || companyCode.isBlank()) {
            throw new IllegalArgumentException("Company code cannot be null or empty.");
        }
        return installationRepository.getInstallationsByCompanyCode(companyCode);
    }

    //Retrieves installations by type with pagination support.
    public List<CompanyInstallation> getInstallationsByType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Installation type cannot be null or empty.");
        }
        return installationRepository.getInstallationsByType(type, pageable);
    }

    //Validates the existence of a company code.
    public boolean validateCompanyCode(String companyCode) {
        if (companyCode == null || companyCode.isBlank()) {
            throw new IllegalArgumentException("Company code cannot be null or empty.");
        }
        return companyInfoRepository.findByCompanyCode(companyCode).isPresent();
    }

    //Finds a company by email.
    public Optional<Company> findCompanyByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        return companyRepository.findByEmail(email);
    }

    //Custom exception for better error handling
    public static class CompanyNotFoundException extends RuntimeException {
        public CompanyNotFoundException(String message) {
            super(message);
        }
    }
}