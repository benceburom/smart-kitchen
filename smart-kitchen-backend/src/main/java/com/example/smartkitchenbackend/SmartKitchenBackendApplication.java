package com.example.smartkitchenbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		SmartKitchenBackendApplication.class
})
public class SmartKitchenBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartKitchenBackendApplication.class, args);
	}
}
