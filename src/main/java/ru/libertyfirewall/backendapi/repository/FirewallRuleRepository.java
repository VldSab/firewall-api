package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;

public interface FirewallRuleRepository extends JpaRepository<FirewallRule, Long> {
}
