package by.mialeshka.effectiveMobile.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserTaskDto {
    private Long id;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Incorrect format email")
    private String username;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max =30, message = "Name should  be between 2 and 30")
    private String name;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 2, max =15, message = "Password should  be between 2 and 15")
    private String password;
    private Date lastPasswordResetDate;
    private List<RoleDto> roles;
    private List<TaskDto> autTaskList;
    private List<TaskDto> perTaskList;
    private List<CommentTaskDto> commentList;
}

