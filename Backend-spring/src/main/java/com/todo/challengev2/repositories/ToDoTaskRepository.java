package com.todo.challengev2.repositories;

import com.todo.challengev2.domain.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ToDoTaskRepository extends JpaRepository<ToDoTask, UUID> {

}
