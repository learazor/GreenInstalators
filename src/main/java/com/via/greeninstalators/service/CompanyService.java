package com.via.greeninstalators.service;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.repository.CompanyRepository;
import com.via.greeninstalators.repository.CompanyInstallationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyInstallationRepository installationRepository;

    public CompanyService(CompanyRepository companyRepository, CompanyInstallationRepository installationRepository) {
        this.companyRepository = companyRepository;
        this.installationRepository = installationRepository;
    }

    public CompanyInfo saveCompanyInfo(CompanyInfo companyInfo) {
        return companyRepository.save(companyInfo);
    }

    public CompanyInstallation saveCompanyInstallation(CompanyInstallation installation) {
        return installationRepository.save(installation);
    }

    public Optional<CompanyInfo> getCompanyInfoByCode(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode);
    }

    public List<CompanyInstallation> getInstallationsByCompanyCode(String companyCode) {
        return installationRepository.getInstallationsByCompanyCode(companyCode);
    }

    public List<CompanyInstallation> getInstallationsByType(String type) {
        return installationRepository.getInstallationsByType(type);
    }
}
