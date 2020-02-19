package com.elotz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
@SpringBootApplication
public class ElotzApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElotzApplication.class, args);
	}

}
