package by.mialeshka.effectiveMobile.controller;

import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.service.TaskService;
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
@RequestMapping(value = "/task")
@Tag(name = "Controller for work tasks")
@AllArgsConstructor
public class TaskRestController implements SecuredRestController {
    TaskService taskService;

    @PostMapping("/save")
    @Operation(summary = "Saving new tasks")
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
        TaskDto newTask = taskService.saveTask(taskDto);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    @Operation(summary = "Update task")
    public ResponseEntity<TaskDto> uppTask(@RequestBody TaskDto taskDto) throws TaskException {
        TaskDto newTask = taskService.uppTask(taskDto);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @PostMapping("/update_status")
    @Operation(summary = "Update status of task")
    public ResponseEntity<TaskDto> uppStatusTask(@RequestBody TaskDto taskDto) throws TaskException {
        TaskDto newTask = taskService.uppStatusTask(taskDto);
        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @PostMapping("/dell/{id}")
    @Operation(summary = "Dell task")
    public ResponseEntity dellTask(@PathVariable("id") Long id) throws TaskException {
        taskService.dellTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_aut_list/{id}")
    @Operation(summary = "List of all author's tasks")
    public ResponseEntity<List<TaskDto>> getAuthorTasksList(@PathVariable("id") Long id,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "2") int size){
        Pageable paging  =  PageRequest.of(page, size);
        return new ResponseEntity<>(taskService.getAllAuthorTasks(id, paging), HttpStatus.OK);
    }

    @GetMapping("/get_per_list/{id}")
    @Operation(summary = "List of all tasks of the performer")
    public ResponseEntity<List<TaskDto>> getPerformTasksList(@PathVariable("id") Long id,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "2") int size){
        Pageable paging  =  PageRequest.of(page, size);
        return new ResponseEntity<>(
                taskService.getAllPerformerTasks(id, paging), HttpStatus.OK);
    }

}
