package com.todo.challengev2.repositories;

import com.todo.challengev2.domain.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelationRepository extends JpaRepository<Relation, UUID> {
}
