package com.taskSBv2312.testSBv2312.repository;

import com.taskSBv2312.testSBv2312.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository <Task, Long> {

}
