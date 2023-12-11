package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.dto.CommentTaskDto;
import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.entity.Task;
import by.mialeshka.effectiveMobile.entity.UserTask;
import by.mialeshka.effectiveMobile.map.TaskMapper;
import by.mialeshka.effectiveMobile.map.UserTaskMapper;
import by.mialeshka.effectiveMobile.references.Status;
import by.mialeshka.effectiveMobile.repository.CommentTaskRepository;
import by.mialeshka.effectiveMobile.repository.TaskRepository;
import by.mialeshka.effectiveMobile.repository.UserTaskRepository;
import by.mialeshka.effectiveMobile.service.TaskService;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@Validated
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private UserTaskRepository userTaskRepository;
    private TaskMapper taskMapper;
    private UserTaskMapper userTaskMapper;
    private CommentTaskRepository commentTaskRepository;
    @Override
    @Transactional(readOnly = false)
    public TaskDto saveTask(@Valid TaskDto taskDto) {
        String username= UserTaskDetailsService.currentUser();
        UserTask userTask = userTaskRepository.findByUsername(username);
        taskDto.setListComments(new ArrayList<CommentTaskDto>());
        taskDto.setAuthor(userTaskMapper.toUserTaskToUserTaskDto(userTask));
        taskDto.setPerformer(userTaskMapper.toUserTaskToUserTaskDto(userTask));
        taskDto.setStatus(Status.INPROGRESS);
        return taskMapper.toTaskToTaskDto(taskRepository.saveAndFlush(taskMapper.toTaskDtoToTask(taskDto)));
    }

    @Override
    @Transactional(readOnly = false)
    public TaskDto uppTask(@Valid TaskDto taskDto) throws TaskException {
        if (UserTaskDetailsService.currentUser().equals(taskDto.getAuthor().getUsername())) {
            return taskMapper.toTaskToTaskDto(taskRepository.saveAndFlush(taskMapper.toTaskDtoToTask(taskDto)));
        } else {
            throw new TaskException("This user can`t change this task");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public TaskDto uppStatusTask(@Valid TaskDto taskDto) throws TaskException {
        if (checkUpdateStatusTask(taskDto)) {
            return taskMapper.toTaskToTaskDto(taskRepository.saveAndFlush(taskMapper.toTaskDtoToTask(taskDto)));
        } else {
            throw new TaskException("This user can`t change this status of task");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void dellTask(long id) throws TaskException {
        if(taskRepository.existsById(id)) {
            commentTaskRepository.deleteAll(commentTaskRepository.getAllByTaskIdFullList(id));
            taskRepository.deleteById(id);
            taskRepository.flush();
        } else {
            throw new TaskException("The task does not exist");
        }
    }

    @Override
    public List<TaskDto> getAllAuthorTasks(long id, Pageable paging) {
        return taskRepository.getTaskListByAuthorId(id, paging).stream()
                .map(p -> taskMapper.toTaskToTaskDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getAllPerformerTasks(long id, Pageable paging) {
        return taskRepository.getTaskListByPerformerId(id, paging).stream()
                .map(p -> taskMapper.toTaskToTaskDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(long id) {
        return taskMapper.toTaskToTaskDto(taskRepository.findById(id).orElse(null));
    }

    private boolean checkUpdateStatusTask(TaskDto taskDto) {
        if (!UserTaskDetailsService.currentUser().equals(taskDto.getAuthor().getUsername())
                && !UserTaskDetailsService.currentUser().equals(taskDto.getPerformer().getUsername())) {
            return false;
        }

        if(UserTaskDetailsService.currentUser().equals(taskDto.getPerformer().getUsername())){
            TaskDto oldTaskDto = taskMapper.toTaskToTaskDto(taskRepository.findById(taskDto.getId()).orElse(new Task()));
            if(!oldTaskDto.getDescription().equals(taskDto.getDescription())) {
                return false;
            }
            if(!oldTaskDto.getHeading().equals(taskDto.getHeading())) {
                return false;
            }

            if(oldTaskDto.getPerformer().getId() != taskDto.getPerformer().getId()) {
                return false;
            }

            if(oldTaskDto.getAuthor().getId() != taskDto.getAuthor().getId()) {
                return false;
            }

            if(!oldTaskDto.getPriority().equals(taskDto.getPriority())) {
                return false;
            }
        }

        return true;
    }
}
