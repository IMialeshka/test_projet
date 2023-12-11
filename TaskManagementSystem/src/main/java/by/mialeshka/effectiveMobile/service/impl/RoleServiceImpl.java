package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.dto.RoleDto;
import by.mialeshka.effectiveMobile.map.RoleMapper;
import by.mialeshka.effectiveMobile.repository.RoleRepository;
import by.mialeshka.effectiveMobile.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@AllArgsConstructor
@Transactional
@Validated
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper roleMapper;
    @Override
    public RoleDto saveRole(@Valid RoleDto roleDto) {
        return roleMapper.toRoleToRoleDto(roleRepository.saveAndFlush(roleMapper.toRoleDtoToRole(roleDto)));
    }
}
