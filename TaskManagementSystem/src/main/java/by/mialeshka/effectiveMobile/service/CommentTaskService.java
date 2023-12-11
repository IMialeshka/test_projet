package by.mialeshka.effectiveMobile.service;

import by.mialeshka.effectiveMobile.dto.CommentTaskDto;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentTaskService {
    CommentTaskDto saveComment(@Valid CommentTaskDto commentTaskDto, long taskId) throws TaskException;

    List<CommentTaskDto> getAllAuthorComment(long id, Pageable paging);
    List<CommentTaskDto> getAllTaskComment(long id, Pageable paging);

}
