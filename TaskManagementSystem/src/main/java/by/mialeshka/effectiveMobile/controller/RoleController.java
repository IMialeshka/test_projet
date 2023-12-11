package by.mialeshka.effectiveMobile.controller;

import by.mialeshka.effectiveMobile.dto.RoleDto;
import by.mialeshka.effectiveMobile.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/role")
@AllArgsConstructor
@Tag(name = "Controller for work roles")
@Validated
public class RoleController {
    RoleService roleService;

    @PostMapping (value = "/save")
    @Operation(summary = "Saving new role")
    public ResponseEntity<RoleDto> addNewRole(@RequestBody RoleDto roleDto) {
        RoleDto newRoleDto = roleService.saveRole(roleDto);
        return new ResponseEntity<>(newRoleDto, HttpStatus.CREATED);
    }
}
