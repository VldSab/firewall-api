package ru.libertyfirewall.backendapi.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.repository.GroupRepository;
import ru.libertyfirewall.backendapi.service.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceStandard implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public GroupContainer create(GroupContainer group) {
        log.info("Creating group with id {}", group.getId());
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(Long id) throws NoSuchGroupException {
        log.info("Deleting group with id {}", id);
        if (groupRepository.findById(id).isEmpty()) {
            throw new NoSuchGroupException("Нет группы с таким ID");
        }
        groupRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean delete(String name) throws NoSuchGroupException {
        log.info("Deleting group with name {}", name);
        if (groupRepository.findByName(name).isEmpty()) {
            throw new NoSuchGroupException("Нет группы с таким именем");
        }
        groupRepository.deleteByName(name);
        return true;
    }

    @Override
    public GroupContainer getByName(String name) throws NoSuchGroupException {
        log.info("Getting group with name {}", name);
        if (groupRepository.findByName(name).isEmpty()) {
            throw new NoSuchGroupException("Нет группы с таким именем");
        }
        return groupRepository.findByName(name).get();
    }

    @Override
    public List<GroupContainer> list() {
        log.info("Fetching list of groups");
        return groupRepository.findAll();
    }
}
