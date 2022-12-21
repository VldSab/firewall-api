package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
