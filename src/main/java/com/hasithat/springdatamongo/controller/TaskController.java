package com.hasithat.springdatamongo.controller;

import com.hasithat.springdatamongo.document.Task;
import com.hasithat.springdatamongo.dto.TaskResponseDTO;
import com.hasithat.springdatamongo.repository.TaskRepository;
import com.hasithat.springdatamongo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<Task> findAllTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable String id){
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@RequestBody Task task, @PathVariable String id){
       return taskService.updateTask(task, id);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
       return taskService.deleteTask(taskId);
    }

    @GetMapping("/{assignee}/{priority}")
    public List<Task> findByAssigneeAndPriority(@PathVariable  String assignee, @PathVariable  String priority){
        return taskService.findByAssigneeAndPriority(assignee,priority);
    }

    @GetMapping("/findByAssigneeAndStoryPoint/{assignee}/{storyPoint}")
    public List<Task> findByAssigneeAndStoryPoint(@PathVariable  String assignee, @PathVariable  int storyPoint){
        return taskService.findByAssigneeAndStoryPoint(assignee,storyPoint);
    }
}
