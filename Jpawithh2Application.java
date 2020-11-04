package com.gen.base;

//Author: Smita Srivastava

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes,
@ComponentScan({"com.gen.services","com.gen.base.rest"}) //Automatic detection of beans for autowiring
@EntityScan("com.gen.entities") //used to identify JPA entities
@EnableJpaRepositories("com.gen.repositories") //Enables and scans for Spring Data repositories
public class Jpawithh2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpawithh2Application.class, args); //This method starts whole Spring Framework
	}
}
