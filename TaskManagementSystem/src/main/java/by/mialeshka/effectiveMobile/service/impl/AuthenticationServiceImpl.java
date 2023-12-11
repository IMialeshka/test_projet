package by.mialeshka.effectiveMobile.service.impl;

import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import by.mialeshka.effectiveMobile.entity.UserTask;
import by.mialeshka.effectiveMobile.repository.UserTaskRepository;
import by.mialeshka.effectiveMobile.security.TaskUsrDetails;
import by.mialeshka.effectiveMobile.security.TokenServiceJwt;
import by.mialeshka.effectiveMobile.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private  AuthenticationManager authenticationManager;

    private TokenServiceJwt tokenServiceJwt;
    private UserTaskRepository userTaskRepository;
    @Override
    public String getToken(UserTaskDto userTaskDto) {
        String userName = userTaskDto.getUsername();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userTaskDto.getPassword()));
        UserTask userTask = userTaskRepository.findByUsername(userName);
        if (userTask == null) {
            throw new UsernameNotFoundException("User with username: " + userName + " not found");
        }
        return tokenServiceJwt.generateAccessToken(new TaskUsrDetails(userTask));
    }
}
