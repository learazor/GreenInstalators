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
        private ApplicationContext applicationContext;
    
        @Test
        void contextLoads() {
            // Verify that the application context loads successfully
            assertThat(applicationContext).isNotNull();
        }
    
        @Test
        void testBeanLoading() {
            // Example: Verify that a specific bean is loaded into the application context
            boolean isBeanPresent = applicationContext.containsBean("exampleBeanName");
            assertThat(isBeanPresent).isTrue();
        }
    
        @Test
        void testPropertyConfiguration() {
            // Example: Verify that a property is correctly configured (replace with actual property test)
            String expectedPropertyValue = "expectedValue";
            String actualPropertyValue = applicationContext.getEnvironment().getProperty("example.property.name");
            assertThat(actualPropertyValue).isEqualTo(expectedPropertyValue);
        }
}
