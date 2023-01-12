package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
