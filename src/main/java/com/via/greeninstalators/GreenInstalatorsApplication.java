package com.via.greeninstalators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenInstalatorsApplication {

    private static final Logger logger = LoggerFactory.getLogger(GreenInstalatorsApplication.class);
    
        public static void main(String[] args) {
            logger.info("Starting GreenInstalatorsApplication...");
            try {
                SpringApplication application = new SpringApplication(GreenInstalatorsApplication.class);
                // Adding customizations like profiles or listeners if needed
                application.setAdditionalProfiles("prod");
                application.run(args);
                logger.info("GreenInstalatorsApplication started successfully.");
            } catch (Exception e) {
                logger.error("Application failed to start: ", e);
            }
        }
}
