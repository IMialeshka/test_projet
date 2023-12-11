package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.dto.CommentTaskDto;
import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.entity.UserTask;
import by.mialeshka.effectiveMobile.map.CommentMapper;
import by.mialeshka.effectiveMobile.map.UserTaskMapper;
import by.mialeshka.effectiveMobile.repository.CommentTaskRepository;
import by.mialeshka.effectiveMobile.repository.UserTaskRepository;
import by.mialeshka.effectiveMobile.service.CommentTaskService;
import by.mialeshka.effectiveMobile.service.TaskService;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Validated
@Transactional(readOnly = true)
public class CommentTaskServiceImpl implements CommentTaskService {
    private CommentTaskRepository commentTaskRepository;
    private CommentMapper commentMapper;
    private UserTaskRepository userTaskRepository;
    private UserTaskMapper userTaskMapper;
    private TaskService taskService;

    @Override
    @Transactional(readOnly = false)
    public CommentTaskDto saveComment(CommentTaskDto commentTaskDto, long taskId) throws TaskException {
        TaskDto taskDto = taskService.getTaskById(taskId);
        if(taskDto == null){
            throw new TaskException("The task not was found");
        } else {
            commentTaskDto.setTask(taskDto);
            String username= UserTaskDetailsService.currentUser();
            UserTask userTask = userTaskRepository.findByUsername(username);
            commentTaskDto.setAuthor(userTaskMapper.toUserTaskToUserTaskDto(userTask));
            return commentMapper.toCommentTaskCommentTaskDto(commentTaskRepository
                    .saveAndFlush(commentMapper.toCommentTaskDtoCommentTask(commentTaskDto)));
        }
    }

    @Override
    public List<CommentTaskDto> getAllAuthorComment(long id, Pageable paging) {
        return commentTaskRepository.getCommentTaskByAuthorId(id, paging).stream()
                .map(p->commentMapper.toCommentTaskCommentTaskDto(p)).collect(Collectors.toList());
    }

    @Override
    public List<CommentTaskDto> getAllTaskComment(long id, Pageable paging) {
        return commentTaskRepository.getCommentTaskByTaskId(id,paging).stream()
                .map(p -> commentMapper.toCommentTaskCommentTaskDto(p)).collect(Collectors.toList());
    }

}
