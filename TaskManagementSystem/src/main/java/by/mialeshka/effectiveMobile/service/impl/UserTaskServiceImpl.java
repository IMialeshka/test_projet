package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import by.mialeshka.effectiveMobile.map.UserTaskMapper;
import by.mialeshka.effectiveMobile.repository.UserTaskRepository;
import by.mialeshka.effectiveMobile.service.UserTaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@Transactional
@Validated
@AllArgsConstructor
public class UserTaskServiceImpl implements UserTaskService {
    private UserTaskRepository userTaskRepository;
    private UserTaskMapper userTaskMapper;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserTaskDto saveUserTask(@Valid UserTaskDto userTaskDto) {
        userTaskDto.setPassword(passwordEncoder.encode(userTaskDto.getPassword()));
        return userTaskMapper.toUserTaskToUserTaskDto(userTaskRepository.saveAndFlush(userTaskMapper.toUserTaskDtoToUserTask(userTaskDto)));
    }
}
