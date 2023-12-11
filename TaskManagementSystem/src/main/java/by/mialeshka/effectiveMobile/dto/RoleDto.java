package by.mialeshka.effectiveMobile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max =30, message = "Name should  be between 2 and 30")
    private String name;
    private List<UserTaskDto> users;
}
