package com.via.greeninstalators;
import com.via.greeninstalators.model.CompanyInfo;
import com.via.greeninstalators.model.CompanyInstallation;
import com.via.greeninstalators.model.user.Company;
import com.via.greeninstalators.repository.CompanyInfoRepository;
import com.via.greeninstalators.repository.CompanyInstallationRepository;
import com.via.greeninstalators.repository.CompanyRepository;
import com.via.greeninstalators.service.AuthenticationService;
import com.via.greeninstalators.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class GreenInstalatorsApplicationTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyInfoRepository companyInfoRepository;


    @MockBean
    private CompanyRepository companyRepository;


    @MockBean
    private CompanyInstallationRepository installationRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testVerifyPassword() {
        String plainPassword = "password123";
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        boolean result = authenticationService.verifyPassword(plainPassword, hashedPassword);

        assertTrue(result, "The plain password should match the hashed password.");
    }

    @Test
    void testAuthenticate_Success() {
        String email = "test@example.com";
        String plainPassword = "password123";
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        Company company = new Company();
        company.setEmail(email);
        company.setPasswordHash(hashedPassword);

        when(companyRepository.findByEmail(email)).thenReturn(Optional.of(company));

        boolean result = authenticationService.authenticate(email, plainPassword);

        assertTrue(result, "Authentication should succeed for valid credentials.");
        verify(companyRepository, times(1)).findByEmail(email);
    }

    @Test
    void testValidateCompanyCode_Exists() {
        String companyCode = "COMP123";
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyCode(companyCode);

        when(companyInfoRepository.findByCompanyCode(companyCode)).thenReturn(Optional.of(companyInfo));

        boolean result = companyService.validateCompanyCode(companyCode);

        assertTrue(result, "The company code should be valid.");
        verify(companyInfoRepository, times(1)).findByCompanyCode(companyCode);
    }

    @Test
    void testSaveCompanyInstallation_Success() {
        String companyCode = "COMP123";
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyCode(companyCode);

        CompanyInstallation installation = new CompanyInstallation();
        installation.setCompany_code(companyCode);

        when(companyInfoRepository.findByCompanyCode(companyCode)).thenReturn(Optional.of(companyInfo));
        when(installationRepository.save(installation)).thenReturn(installation);

        CompanyInstallation savedInstallation = companyService.saveCompanyInstallation(installation);

        assertNotNull(savedInstallation, "The installation should be saved successfully.");
        verify(companyInfoRepository, times(1)).findByCompanyCode(companyCode);
        verify(installationRepository, times(1)).save(installation);
    }
}
