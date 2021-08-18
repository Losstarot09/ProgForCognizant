package com.taskSBv253.testSBv253.repository;

import com.taskSBv253.testSBv253.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
