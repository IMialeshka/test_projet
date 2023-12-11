package by.mialeshka.effectiveMobile.map;

import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserTaskMapper.class})
public interface TaskMapper {
    @Mappings({
            @Mapping(target = "listComments", ignore = true)
    })
    Task toTaskDtoToTask(TaskDto taskDto);


    @Mappings({
            @Mapping(target = "listComments", ignore = true)
    })
    TaskDto toTaskToTaskDto(Task task);

}
