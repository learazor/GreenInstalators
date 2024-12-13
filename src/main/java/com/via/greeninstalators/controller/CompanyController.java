package com.via.greeninstalators.controller;

import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.service.CompanyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/addInstallation")
    public ResponseEntity<Map<String, String>> addInstallation(@Valid @RequestBody CompanyInstallation installation, HttpSession session) {
        //Check if a company is logged in
        String companyCode = (String) session.getAttribute("companyCode");

        if (companyCode == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in as a company to perform this action.");
        }

        //Set the company code for the installation
        installation.setCompany_code(companyCode);

        //Save the installation
        try {
                companyService.saveCompanyInstallation(installation);
                return ResponseEntity.ok(Map.of("message", "Installation added successfully"));
            } catch (Exception e) {
                log.error("Error saving installation: {}", e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Failed to add installation"));
            }
    }

    //Endpoint used for debugging
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
    public ResponseEntity<String> createInfo(@Valid @RequestBody CompanyInfo companyInfo) {
        try {
            companyService.saveCompanyInfo(companyInfo);
            return ResponseEntity.ok("CompanyInfo saved successfully!");
        } catch (Exception e) {
            log.error("Error saving CompanyInfo: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save CompanyInfo.");
        }
    }

    @GetMapping("/info/{companyCode}")
    public ResponseEntity<Optional<CompanyInfo>> getCompanyInfo(@PathVariable String companyCode) {
        try {
            Optional<CompanyInfo> companyInfo = companyService.getCompanyInfoByCode(companyCode);
            return ResponseEntity.ok(companyInfo);
        } catch (Exception e) {
            log.error("Error retrieving company info: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/installations/{companyCode}")
    public ResponseEntity<List<CompanyInstallation>> getInstallationsByCompanyCode(@PathVariable String companyCode) {
        try {
            List<CompanyInstallation> installations = companyService.getInstallationsByCompanyCode(companyCode);
            return ResponseEntity.ok(installations);
        } catch (Exception e) {
            log.error("Error retrieving installations: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/installations/type/{type}")
    public ResponseEntity<List<CompanyInstallation>> getInstallationsByType(@PathVariable String type) {
        try {
            List<CompanyInstallation> installations = companyService.getInstallationsByType(type);
            return ResponseEntity.ok(installations);
        } catch (Exception e) {
            log.error("Error retrieving installations by type: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
