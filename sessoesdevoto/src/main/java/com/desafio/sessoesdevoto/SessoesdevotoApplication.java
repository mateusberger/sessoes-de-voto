package com.desafio.sessoesdevoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SessoesdevotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessoesdevotoApplication.class, args);
	}

}
