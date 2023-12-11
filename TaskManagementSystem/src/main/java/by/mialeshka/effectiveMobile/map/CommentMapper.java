package by.mialeshka.effectiveMobile.map;

import by.mialeshka.effectiveMobile.dto.CommentTaskDto;
import by.mialeshka.effectiveMobile.entity.CommentTask;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserTaskMapper.class, TaskMapper.class})
public interface CommentMapper {
    CommentTaskDto toCommentTaskCommentTaskDto(CommentTask commentTask);
    CommentTask toCommentTaskDtoCommentTask(CommentTaskDto commentTaskDto);
}
