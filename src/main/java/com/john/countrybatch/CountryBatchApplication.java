package com.john.countrybatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CountryBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryBatchApplication.class, args);
	}

}
