package com.fiesta.invitaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class InvitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvitacionesApplication.class, args);
	}

}
