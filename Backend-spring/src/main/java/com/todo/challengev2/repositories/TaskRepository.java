package com.todo.challengev2.repositories;

import com.todo.challengev2.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    // TODO: 13/9/2022 UUID search works with '=', but not with LIKE
    @Query("SELECT t FROM Task t WHERE t.id = CAST(:searchArgs AS org.hibernate.type.UUIDCharType) OR t.name LIKE %:searchArgs% OR t.priority=:searchArgs")
    List<Task> search(@Param("searchArgs") String searchArgs);
}
