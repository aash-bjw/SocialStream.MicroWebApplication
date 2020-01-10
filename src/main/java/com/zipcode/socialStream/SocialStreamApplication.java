package com.zipcode.socialStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SocialStreamApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("DATABASE_USERNAME"));
		SpringApplication.run(SocialStreamApplication.class, args);
	}

}
