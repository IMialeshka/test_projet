package by.mialeshka.effectiveMobile.service;

import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import java.util.List;

public interface TaskService {
    TaskDto saveTask(@Valid TaskDto taskDto);
    TaskDto uppTask(@Valid TaskDto taskDto) throws TaskException;
    TaskDto uppStatusTask(@Valid TaskDto taskDto) throws TaskException;
    void dellTask(long id) throws TaskException;
    List<TaskDto> getAllAuthorTasks(long id, Pageable paging);
    List<TaskDto> getAllPerformerTasks(long id, Pageable paging);
    TaskDto getTaskById(long id);
}
