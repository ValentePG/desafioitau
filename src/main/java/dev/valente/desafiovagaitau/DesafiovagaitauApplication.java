package dev.valente.desafiovagaitau;

import dev.valente.desafiovagaitau.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class DesafiovagaitauApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiovagaitauApplication.class, args);
	}

}
