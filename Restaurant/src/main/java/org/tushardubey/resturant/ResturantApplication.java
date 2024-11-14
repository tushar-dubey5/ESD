package org.tushardubey.resturant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ResturantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResturantApplication.class, args);
	}

}
