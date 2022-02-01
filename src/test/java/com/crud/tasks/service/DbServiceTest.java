package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    void shouldReturnEmptyList() {
        //Given & When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertThat(tasks).isNotNull();
        assertEquals(0, tasks.size());
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> tasksMock = List.of(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasksMock);
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertThat(tasks).isNotNull();
        assertEquals(1, tasks.size());
        assertEquals("title", tasks.get(0).getTitle());
        assertEquals("content", tasks.get(0).getContent());
    }

    @Test
    void shouldReturnEmptyOptional() {
        //Given & When
        Optional<Task> task = dbService.getTask(1L);
        //Then
        assertTrue(task.isEmpty());
    }

    @Test
    void shouldGetTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.findById(taskMock.getId())).thenReturn(Optional.ofNullable(taskMock));
        //When
        Optional<Task> task = dbService.getTask(taskMock.getId());
        //Then
        assertTrue(task.isPresent());
        assertEquals("title", task.get().getTitle());
        assertEquals("content", task.get().getContent());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);
        //When
        Task task = dbService.saveTask(taskMock);
        //Then
        assertThat(task).isNotNull();
        assertEquals(taskMock.getId(), task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }
}