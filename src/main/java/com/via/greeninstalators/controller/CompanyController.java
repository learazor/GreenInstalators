package com.via.greeninstalators.controller;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {

        this.companyService = companyService;
    }

    @PostMapping("/addInstallation")
    public ResponseEntity<?> addInstallation(@RequestBody CompanyInstallation installation, HttpSession session) {
        //Check if a company is logged in
        String companyCode = (String) session.getAttribute("companyCode");

        if (companyCode == null) {
            return ResponseEntity.status(401).body("You must be logged in as a company to perform this action.");
        }

        //Set the company code for the installation
        installation.setCompany_code(companyCode);

        //Save the installation
        companyService.saveCompanyInstallation(installation);

        return ResponseEntity.ok(Map.of("message", "Installation added successfully"));
    }

    /* Endpoint used for debugging
    @PostMapping("/saveInstallation")
    public String createInstallation(@RequestBody CompanyInstallation companyInstallation) {
        CompanyInstallation savedInstallation = companyService.saveCompanyInstallation(companyInstallation);
        if (savedInstallation != null) {
            return "CompanyInstallation saved successfully!";
        } else {
            return "Failed to save CompanyInstallation.";
        }
    }

    @PostMapping("/saveInfo")
    public String createInfo(@RequestBody CompanyInfo companyInfo) {
        CompanyInfo savedInfo = companyService.saveCompanyInfo(companyInfo);
        if (savedInfo != null) {
            return "CompanyInfo saved successfully!";
        } else {
            return "Failed to save CompanyInfo.";
        }
    }

    @GetMapping("/info/{companyCode}")
    public Optional<CompanyInfo> getCompanyInfo(@PathVariable String companyCode) {
        return companyService.getCompanyInfoByCode(companyCode);
    }

    @GetMapping("/installations/{companyCode}")
    public List<CompanyInstallation> getInstallationsByCompanyCode(@PathVariable String companyCode) {
        return companyService.getInstallationsByCompanyCode(companyCode);
    }

    @GetMapping("/installations/type/{type}")
    public List<CompanyInstallation> getInstallationsByType(@PathVariable String type) {
        return companyService.getInstallationsByType(type);
    }*/
}
