package ru.libertyfirewall.backendapi.repository.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.repository.GroupRepository;

import java.util.Optional;

public class GroupRepositoryStandard implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<GroupContainer> findByName(String name) {
        Query sqlGetQuery = entityManager.createNativeQuery(
                "SELECT id, name, comment " +
                        "FROM public.groups " +
                        "WHERE name = :name",
                GroupContainer.class
        );
        sqlGetQuery.setParameter("name", name);
        GroupContainer result = (GroupContainer) sqlGetQuery.getSingleResult();
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Override
    public GroupContainer findById(Long id) {
        Query sqlGetQuery = entityManager.createNativeQuery(
                "SELECT id, name, comment " +
                        "FROM public.groups " +
                        "WHERE id = :id",
                GroupContainer.class
        );
        sqlGetQuery.setParameter("id", id);
        return (GroupContainer) sqlGetQuery.getSingleResult();
    }

    @Override
    public boolean deleteByName(String name) {
        return false;
    }
}
