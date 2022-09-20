package com.todo.challengev2.relations.functionalities.delete;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RelationDeleteServiceImpl implements RelationDeleteService{

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public void deleteRelation(UUID id) {
        relationRepository.delete(relationRepository.findById(id).get());
    }
}
