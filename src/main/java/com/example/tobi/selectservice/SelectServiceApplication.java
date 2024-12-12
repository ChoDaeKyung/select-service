package com.example.tobi.selectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SelectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectServiceApplication.class, args);
	}

}
