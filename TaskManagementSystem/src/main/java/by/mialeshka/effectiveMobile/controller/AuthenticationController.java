package by.mialeshka.effectiveMobile.controller;

import by.mialeshka.effectiveMobile.dto.JwtTokenDto;
import by.mialeshka.effectiveMobile.dto.UserTaskDto;
import by.mialeshka.effectiveMobile.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
@Tag(name = "Controller for authentication")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    @Operation(summary = "Generate token")
    public ResponseEntity<JwtTokenDto> login(@RequestBody UserTaskDto userTaskDto) {
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(authenticationService.getToken(userTaskDto));
        jwtTokenDto.setUsername(userTaskDto.getUsername());
        return new ResponseEntity<>(jwtTokenDto, HttpStatus.OK);
    }

}
