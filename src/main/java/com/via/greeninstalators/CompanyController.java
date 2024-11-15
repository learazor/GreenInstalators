package com.via.greeninstalators.controller;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Endpoint to save CompanyInstallation
    @PostMapping("/saveInstallation")
    public String createInstallation(@RequestBody CompanyInstallation companyInstallation) {
        CompanyInstallation savedInstallation = companyService.saveCompanyInstallation(companyInstallation);
        if (savedInstallation != null) {
            return "CompanyInstallation saved successfully!";
        } else {
            return "Failed to save CompanyInstallation.";
        }
    }

    // Endpoint to save CompanyInfo
    @PostMapping("/saveInfo")
    public String createInfo(@RequestBody CompanyInfo companyInfo) {
        CompanyInfo savedInfo = companyService.saveCompanyInfo(companyInfo);
        if (savedInfo != null) {
            return "CompanyInfo saved successfully!";
        } else {
            return "Failed to save CompanyInfo.";
        }
    }

    // Endpoint to get CompanyInfo by company code
    @GetMapping("/info/{companyCode}")
    public Optional<CompanyInfo> getCompanyInfo(@PathVariable String companyCode) {
        return companyService.getCompanyInfoByCode(companyCode);
    }

    // Endpoint to get all installations by company code
    @GetMapping("/installations/{companyCode}")
    public List<CompanyInstallation> getInstallationsByCompanyCode(@PathVariable String companyCode) {
        return companyService.getInstallationsByCompanyCode(companyCode);
    }

    // Endpoint to get installations by type
    @GetMapping("/installations/type/{type}")
    public List<CompanyInstallation> getInstallationsByType(@PathVariable String type) {
        return companyService.getInstallationsByType(type);
    }
}
