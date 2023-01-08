package com.hasithat.springdatamongo.service;

import com.hasithat.springdatamongo.document.Task;
import com.hasithat.springdatamongo.dto.TaskResponseDTO;
import com.hasithat.springdatamongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public TaskResponseDTO getTaskById(String id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        TaskResponseDTO taskResponseDTO= new TaskResponseDTO();
        if(optionalTask.isPresent()){
            taskResponseDTO.setMessage("Success");
            taskResponseDTO.setTask(optionalTask.get());
        }else{
            taskResponseDTO.setMessage("No Task found with id "+id);
        }
        return taskResponseDTO;
    }

    public TaskResponseDTO updateTask(Task task, String id){
        TaskResponseDTO existingTask = this.getTaskById(id);
        TaskResponseDTO taskResponseDTO= new TaskResponseDTO();
        Task updatedTask=null;
        if(existingTask.getTask()!=null){
            existingTask.getTask().setAssignee(task.getAssignee());
            existingTask.getTask().setPriority(task.getPriority());
            existingTask.getTask().setDescription(task.getDescription());
            existingTask.getTask().setStoryPoint(task.getStoryPoint());
            updatedTask=taskRepository.save(existingTask.getTask());
            if(updatedTask!=null){
                taskResponseDTO.setTask(updatedTask);
                taskResponseDTO.setMessage("Success");
            }else{
                taskResponseDTO.setMessage("Errorr occured while updating Task....");
            }

        }else{
            taskResponseDTO.setMessage("No Task found with id "+id);
        }
        return taskResponseDTO;
    }

    public String deleteTask(String taskId){
        TaskResponseDTO existingTask = this.getTaskById(taskId);
        if(existingTask.getTask()!=null){
            taskRepository.delete(existingTask.getTask());
            return "Task with id "+taskId+" deleted.";
        }else{
            return "No Task found with id "+taskId;
        }

    }

    public List<Task> findByAssigneeAndPriority(String assignee, String priority){
        return taskRepository.findByAssigneeAndPriority(assignee,priority);
    }

    public List<Task> findByAssigneeAndStoryPoint(String assignee, int storyPoint){
        return taskRepository.findByAssigneeAndStoryPoint(assignee,storyPoint);
    }
}
