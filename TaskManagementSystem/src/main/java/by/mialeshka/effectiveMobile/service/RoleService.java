package by.mialeshka.effectiveMobile.service;

import by.mialeshka.effectiveMobile.dto.RoleDto;

import jakarta.validation.Valid;

public interface RoleService {
    RoleDto saveRole(@Valid RoleDto roleDto);
}
