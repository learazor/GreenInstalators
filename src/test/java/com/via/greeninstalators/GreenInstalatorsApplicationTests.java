package com.via.greeninstalators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
