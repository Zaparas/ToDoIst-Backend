package com.todo.challengev2.relations.functionalities;

import com.todo.challengev2.relations.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelationRepository extends JpaRepository<Relation, UUID> {
}
