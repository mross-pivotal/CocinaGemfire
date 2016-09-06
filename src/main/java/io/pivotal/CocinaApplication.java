package io.pivotal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@ImportResource("classpath:config/sdg-context.xml")
@SpringBootApplication
@EnableGemfireRepositories
public class CocinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocinaApplication.class, args);
	}
}
