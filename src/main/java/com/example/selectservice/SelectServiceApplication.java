package com.example.selectservice;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class SelectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelectServiceApplication.class, args);
	}

}
