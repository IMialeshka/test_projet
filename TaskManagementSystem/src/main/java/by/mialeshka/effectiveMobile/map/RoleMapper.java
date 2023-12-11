package by.mialeshka.effectiveMobile.map;

import by.mialeshka.effectiveMobile.dto.RoleDto;
import by.mialeshka.effectiveMobile.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleToRoleDto(Role role);
    Role toRoleDtoToRole(RoleDto roleDto);

    @Named("mapRoleList")
    default List<RoleDto> toRoles(List<Role> roleList) {
        return roleList.stream().peek(r -> r.setUsers(null)).map(this::toRoleToRoleDto).collect(Collectors.toList());
    }
}
