package ru.libertyfirewall.backendapi.service;

import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.model.GroupContainer;

import java.util.List;

public interface GroupService {
    GroupContainer create(GroupContainer group);
    boolean delete(Long id) throws NoSuchGroupException;
    boolean delete(String name) throws NoSuchGroupException;
    GroupContainer getByName(String name) throws NoSuchGroupException;
    List<GroupContainer> list();
}
