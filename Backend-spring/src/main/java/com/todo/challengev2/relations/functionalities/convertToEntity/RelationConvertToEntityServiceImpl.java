package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RelationConvertToEntityServiceImpl implements RelationConvertToEntityService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        Relation relation = new Relation();
        relation.setRelationType(relationInDTO.getRelationType());
        Optional<Task> optionalTask = taskRepository.findById(relationInDTO.getTaskId());
        if (optionalTask.isPresent()) {
            relation.setTask(optionalTask.get());
            return relation;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Entity - Task with id: "+ relationInDTO.getTaskId() +" not found.");
    }
}
