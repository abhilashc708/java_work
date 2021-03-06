package com.testcode.springbootrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.testcode.springbootrestapi.dto.FileStorageProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class UserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
