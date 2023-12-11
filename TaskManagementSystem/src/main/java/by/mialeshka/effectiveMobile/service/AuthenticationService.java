package by.mialeshka.effectiveMobile.service;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;

public interface AuthenticationService {
    String getToken(UserTaskDto userTaskDto);
}
