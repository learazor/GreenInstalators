package com.via.greeninstalators.service;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.model.user.Company;
import com.via.greeninstalators.repository.CompanyInfoRepository;
import com.via.greeninstalators.repository.CompanyInstallationRepository;
import com.via.greeninstalators.repository.CompanyRepository;
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

    public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
        return companyInfoRepository.save(companyInfo);
    }

    public CompanyInstallation saveCompanyInstallation(CompanyInstallation installation) {
        // Validate if the company exists
        Optional<CompanyInfo> companyInfoOptional = companyInfoRepository.findByCompanyCode(installation.getCompanyCode());
        if (companyInfoOptional.isEmpty()) {
            throw new IllegalArgumentException("Company with code " + installation.getCompanyCode() + " does not exist.");
        }

        // Save the installation
        return installationRepository.save(installation);
    }

    public Optional<CompanyInfo> getCompanyInfoByCode(String companyCode) {
        return companyInfoRepository.findByCompanyCode(companyCode);
    }

    public List<CompanyInstallation> getInstallationsByCompanyCode(String companyCode) {
        return installationRepository.getInstallationsByCompanyCode(companyCode);
    }

    public List<CompanyInstallation> getInstallationsByType(String type) {
        return installationRepository.getInstallationsByType(type);
    }

    public boolean validateCompanyCode(String companyCode) {
        return companyInfoRepository.findByCompanyCode(companyCode).isPresent();
    }

    public Optional<Company> findCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }
}
