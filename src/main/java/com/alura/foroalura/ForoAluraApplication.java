package com.alura.foroalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EntityScan("com.alura.foroalura.domain") // Paquete donde se encuentran tus
// entidades JPA
// Especifica el paquete donde se encuentran los repositorios JPA
@EnableJpaRepositories(basePackages = "com.alura.foroalura.repository")
public class ForoAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoAluraApplication.class, args);
	}

}
