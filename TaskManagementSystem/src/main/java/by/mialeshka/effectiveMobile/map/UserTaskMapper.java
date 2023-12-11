package by.mialeshka.effectiveMobile.map;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import by.mialeshka.effectiveMobile.entity.UserTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserTaskMapper {
    @Mappings({
            @Mapping(target = "password", constant = ""),
            @Mapping(target = "autTaskList", ignore = true),
            @Mapping(target = "perTaskList", ignore = true),
            @Mapping(target = "commentList",ignore = true),
            @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRoleList")
    })
    UserTaskDto toUserTaskToUserTaskDto(UserTask userTask);
    UserTask toUserTaskDtoToUserTask(UserTaskDto userTaskDto);
}
