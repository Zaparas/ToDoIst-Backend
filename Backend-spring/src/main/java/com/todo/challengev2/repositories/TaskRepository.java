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

    // TODO: 13/9/2022 It works 
    @Query("SELECT t FROM Task t WHERE t.id LIKE CAST(?1 AS org.hibernate.type.UUIDCharType) OR t.name LIKE %?1% OR t.priority LIKE %?1%")
    List<Task> search(@Param("args") String args);

    // TODO: 13/9/2022 Error fixed, returns nothing
    @Query("SELECT t FROM Task t WHERE t.id LIKE CONCAT(?#{#args.id}, '%') OR t.name LIKE CONCAT(?#{#args.name}, '%')")
    List<Task> searchDTO(@Param("args") TaskIndexDTO args);
}
