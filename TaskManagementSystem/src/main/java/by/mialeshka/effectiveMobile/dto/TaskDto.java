package by.mialeshka.effectiveMobile.dto;

import by.mialeshka.effectiveMobile.references.Priority;
import by.mialeshka.effectiveMobile.references.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {
    private long id;
    @NotEmpty(message = "Heading should not be empty")
    @Size(min = 2, max =50, message = "Heading should  be between 2 and 50")
    private String heading;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max =200, message = "Description should  be between 2 and 200")
    private String description;
    @NotNull(message = "Enter status")
    private Status status;
    @NotNull(message = "Enter priority")
    private Priority priority;
    @NotNull(message = "Enter author")
    private UserTaskDto author;
    @NotNull(message = "Enter performer")
    private UserTaskDto performer;
    private List<CommentTaskDto> listComments;

}
