package by.mialeshka.effectiveMobile.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentTaskDto {
    private long id;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max =200, message = "Description should  be between 2 and 200")
    private String description;
    @NotNull(message = "Choose author")
    private UserTaskDto author;
    private TaskDto task;
}
