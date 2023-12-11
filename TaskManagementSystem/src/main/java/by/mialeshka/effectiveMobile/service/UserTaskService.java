package by.mialeshka.effectiveMobile.service;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import jakarta.validation.Valid;


public interface UserTaskService {
    UserTaskDto saveUserTask (@Valid UserTaskDto userTaskDto);
}
