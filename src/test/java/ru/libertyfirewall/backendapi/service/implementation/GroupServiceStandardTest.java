package ru.libertyfirewall.backendapi.service.implementation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.libertyfirewall.backendapi.exeptions.group.NoSuchGroupException;
import ru.libertyfirewall.backendapi.model.GroupContainer;
import ru.libertyfirewall.backendapi.repository.GroupRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceStandardTest {
    @Mock
    static GroupRepository groupRepositoryMock;
    static GroupContainer group1;
    static GroupContainer group2;

    @BeforeAll
    static void init() {
        group1 = GroupContainer.builder()
                .id(1L)
                .name("First group")
                .ipContainer(List.of("127.0.0.1"))
                .build();

        group2 = GroupContainer.builder()
                .id(2L)
                .name("First group")
                .ipContainer(List.of("127.0.0.1"))
                .build();
    }

    @Test
    void create() {
        when(groupRepositoryMock.save(group1)).thenReturn(group1);
        GroupServiceStandard serviceStandard = new GroupServiceStandard(groupRepositoryMock);
        assertEquals(serviceStandard.create(group1), group1);
    }

    @Test
    void delete() throws NoSuchGroupException {
        when(groupRepositoryMock.findById(1L)).thenReturn(Optional.of(group1));
        given(groupRepositoryMock.findById(3L)).willAnswer(invocation -> {throw new NoSuchGroupException("Нет группы с таким ID");});
        Set<GroupContainer> groupContainerSet = new HashSet<>();
        when(groupRepositoryMock.save(group1)).thenReturn(group1);
        groupContainerSet.add(groupRepositoryMock.save(group1));
        GroupServiceStandard serviceStandard = new GroupServiceStandard(groupRepositoryMock);
        assertTrue(serviceStandard.delete(1L));
        NoSuchGroupException exception = assertThrows(
                NoSuchGroupException.class,
                () -> serviceStandard.delete(3L)
        );
        assertEquals("Нет группы с таким ID", exception.getMessage());
    }

    @Test
    void testDelete() {
    }

    @Test
    void getByName() {
    }

    @Test
    void list() {
    }
}