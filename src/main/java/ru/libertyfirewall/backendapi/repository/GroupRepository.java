package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.GroupContainer;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupContainer, Long> {
    Optional<GroupContainer> findByName(String name);
    boolean deleteByName(String name);
}
