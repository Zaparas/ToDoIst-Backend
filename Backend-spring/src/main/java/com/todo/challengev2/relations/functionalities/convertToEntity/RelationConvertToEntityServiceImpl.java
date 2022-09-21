package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RelationConvertToEntityServiceImpl implements RelationConvertToEntityService {

    /**
     * Converts a RelationInDTO to a relation object
     * @param relationInDTO the target object to be converted
     * @return a relation object based on the input
     */
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Relation convertToEntity(RelationFullDTO relationFullDTO) {
        Relation relation = new Relation();
        relation.setRelationType(relationFullDTO.getRelationType());
        Optional<Task> optionalTask = taskRepository.findById(relationFullDTO.getTaskId());
        if (optionalTask.isPresent()) {
            relation.setTask(optionalTask.get());
            return relation;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Entity - Task with id: "+ relationFullDTO.getTaskId() +" not found.");
    }

    @Override
    public Relation convertToEntity(RelationRestrictedDTO relationRestrictedDTO) {
        Relation relation = new Relation();
        relation.setRelationType(relationRestrictedDTO.getRelationType());
        Optional<Task> optionalTask = taskRepository.findById(relationRestrictedDTO.getTaskId());
        if (optionalTask.isPresent()) {
            relation.setTask(optionalTask.get());
            return relation;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Entity - Task with id: "+ relationRestrictedDTO.getTaskId() +" not found.");
    }
}
