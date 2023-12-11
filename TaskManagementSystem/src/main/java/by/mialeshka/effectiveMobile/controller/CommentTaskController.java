package by.mialeshka.effectiveMobile.controller;

import by.mialeshka.effectiveMobile.dto.CommentTaskDto;
import by.mialeshka.effectiveMobile.service.CommentTaskService;
import by.mialeshka.effectiveMobile.utill.SecuredRestController;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@AllArgsConstructor
@Tag(name = "Controller for work comments")
public class CommentTaskController implements SecuredRestController {
    CommentTaskService commentTaskService;

    @PostMapping("/save/{taskId}")
    @Operation(summary = "Saving new comment")
    public ResponseEntity<CommentTaskDto> saveComment(@PathVariable("taskId") long taskId,
                                                      @RequestBody CommentTaskDto commentTaskDto) throws TaskException {

        CommentTaskDto newComment = commentTaskService.saveComment(commentTaskDto, taskId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/comments_task/{taskId}")
    @Operation(summary = "List of all comments of the task")
    public ResponseEntity<List<CommentTaskDto>> getTaskCommentList(@PathVariable("taskId") long taskId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(commentTaskService.getAllTaskComment(taskId, pageable),HttpStatus.OK);
    }

    @GetMapping("/comments_author/{authorId}")
    @Operation(summary = "List of all comments of the author")
    public ResponseEntity<List<CommentTaskDto>> getAuthorCommentList(@PathVariable("authorId") long authorId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(commentTaskService.getAllAuthorComment(authorId, pageable),HttpStatus.OK);
    }
}
