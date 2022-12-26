package ru.libertyfirewall.backendapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.libertyfirewall.backendapi.enumeration.Status;
import ru.libertyfirewall.backendapi.model.Server;
import ru.libertyfirewall.backendapi.repository.ServerRepository;

@SpringBootApplication
public class BackendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository) {
		return args -> {
			serverRepository.save(new Server(
					null,
					"172.0.0.1",
					"Ubuntu",
					"16 GB",
					"Personal computer",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));

			serverRepository.save(new Server(
					null,
					"172.0.0.2",
					"Windows",
					"32 GB",
					"Host",
					"http://localhost:8080/server/image/server2.png",
					Status.SERVER_DOWN));
		};
	}

}
