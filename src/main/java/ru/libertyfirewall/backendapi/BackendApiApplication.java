package ru.libertyfirewall.backendapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.repository.RuleRepository;

@SpringBootApplication
@AllArgsConstructor
public class BackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}

}
