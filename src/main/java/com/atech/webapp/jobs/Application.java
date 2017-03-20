package com.atech.webapp.jobs;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@EnableScheduling
@EnableJms
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	
	public static void main(String[] args) throws Exception {
		
		FileSystemUtils.deleteRecursively(new File("activemq-data"));
		
        SpringApplication.run(Application.class);
    }
	
}
