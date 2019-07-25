package com.sarki.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MicroApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MicroApplication.class, args);
	}

}
