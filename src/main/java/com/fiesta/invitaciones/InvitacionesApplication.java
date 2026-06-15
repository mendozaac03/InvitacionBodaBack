package com.fiesta.invitaciones;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class InvitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvitacionesApplication.class, args);
	}
	@Bean
	public CommandLineRunner testMongoConfig(
			@Value("${spring.mongodb.uri}")
			String uri) {

		return args -> {
			System.out.println(
					"MONGODB URI = " + uri
			);
		};
	}
}
