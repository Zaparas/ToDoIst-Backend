package com.todo.challengev2.repositories;

import com.todo.challengev2.domain.Task;
import com.todo.challengev2.dto.TaskIndexDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.id LIKE CONCAT(?#{#args.id}, '%') " +
            "OR t.name LIKE CONCAT(?#{#args.name}, '%') " +
            "OR t.priority = ?#{#args.priority} " +
            "OR (t.dueDate > ?#{#args.afterDate} AND t.dueDate < ?#{#args.beforeDate})")
    List<Task> search(@Param("args") TaskIndexDTO args);
}
