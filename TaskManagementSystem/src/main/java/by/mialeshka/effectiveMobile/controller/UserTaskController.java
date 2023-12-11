package by.mialeshka.effectiveMobile.controller;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import by.mialeshka.effectiveMobile.service.UserTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
@Tag(name = "Controller for work users")
public class UserTaskController {
    UserTaskService userTaskService;

    @PostMapping(value = "/save")
    @Operation(summary = "Saving new user")
    public ResponseEntity<UserTaskDto> addNewTaskUser(@RequestBody UserTaskDto userTaskDto) {
        return new ResponseEntity<>(userTaskService.saveUserTask(userTaskDto), HttpStatus.CREATED);
    }
}
