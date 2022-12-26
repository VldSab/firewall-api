package ru.libertyfirewall.backendapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.libertyfirewall.backendapi.enumeration.Status;
import ru.libertyfirewall.backendapi.enumeration.rules.Action;
import ru.libertyfirewall.backendapi.enumeration.rules.Protocol;
import ru.libertyfirewall.backendapi.model.Rule;
import ru.libertyfirewall.backendapi.model.Server;
import ru.libertyfirewall.backendapi.repository.RuleRepository;
import ru.libertyfirewall.backendapi.repository.ServerRepository;
import ru.libertyfirewall.backendapi.util.AdditionalRuleParameters;

@SpringBootApplication
public class BackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RuleRepository ruleRepository) {
		return args -> {
			ruleRepository.save(new Rule(
					null,
					Action.ALERT,
					Protocol.TCP,
					"any",
					"80",
					"any",
					"any",
					AdditionalRuleParameters.MESSAGE)
			);
		};
	}

}
