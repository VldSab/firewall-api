package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.rules.DLPRule;

public interface DLPRuleRepository extends JpaRepository<DLPRule, Long> {
}
