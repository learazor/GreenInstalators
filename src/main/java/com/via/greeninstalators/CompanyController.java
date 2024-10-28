package com.via.greeninstalators;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping("/saveInstallation")
    public String createInstallation(@RequestBody CompanyInstallation companyInstallation) {
        boolean isSaved = companyRepository.saveCompanyInstallation(companyInstallation);
        if (isSaved) {
            return "CompanyInstallation saved successfully";
        } else {
            return "Failed to save CompanyInstallation.";
        }
    }

    @PostMapping("/saveInfo")
    public String createInfo(@RequestBody CompanyInfo companyInfo) {
        boolean isSaved = companyRepository.saveCompanyInfo(companyInfo);
        if (isSaved) {
            return "CompanyInfo saved successfully";
        } else {
            return "Failed to save CompanyInfo.";
        }
    }
}
