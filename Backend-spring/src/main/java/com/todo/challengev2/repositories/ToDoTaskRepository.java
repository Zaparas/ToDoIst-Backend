package com.todo.challengev2.repositories;

import com.todo.challengev2.domain.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ToDoTaskRepository extends CrudRepository<ToDoTask, UUID> {

    @Override
    List<ToDoTask> findAll();

}
