package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.rules.FirewallRule;

public interface RuleRepository extends JpaRepository<FirewallRule, Long> {
}
