package com.hasithat.springdatamongo.repository;

import com.hasithat.springdatamongo.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByAssigneeAndPriority(String assignee, String priority);

    @Query("{assignee: ?0, storyPoint: ?1}")
    List<Task> findByAssigneeAndStoryPoint(String assignee, int storyPoint);
}
