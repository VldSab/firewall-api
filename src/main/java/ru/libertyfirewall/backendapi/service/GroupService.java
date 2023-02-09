package ru.libertyfirewall.backendapi.service;

import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupExeption;
import ru.libertyfirewall.backendapi.model.GroupContainer;

import java.util.List;

public interface GroupService {
    GroupContainer create(GroupContainer group);
    boolean delete(Long id) throws NoSuchGroupExeption;
    boolean delete(String name) throws NoSuchGroupExeption;
    GroupContainer getByName(String name) throws NoSuchGroupExeption;
    List<GroupContainer> list();
}
