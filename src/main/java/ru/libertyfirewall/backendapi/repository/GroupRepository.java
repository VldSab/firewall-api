package ru.libertyfirewall.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.libertyfirewall.backendapi.model.GroupContainer;

import java.util.Optional;

public interface GroupRepository {
    Optional<GroupContainer> findByName(String name);

    GroupContainer findById(Long id);
    boolean deleteByName(String name);
}
