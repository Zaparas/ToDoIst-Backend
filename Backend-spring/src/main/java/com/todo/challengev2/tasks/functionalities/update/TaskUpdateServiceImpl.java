package com.todo.challengev2.tasks.functionalities.update;

import com.todo.challengev2.relations.functionalities.convertToEntity.RelationConvertToEntityService;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.functionalities.TaskRepository;
import com.todo.challengev2.tasks.utils.dtos.TaskRestrictedDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private RelationConvertToEntityService relationConvertToEntityService;

    @Override
    public TaskFullDTO updateTask(UUID id, TaskFullDTO taskFullDTO) {
        // TODO: 12/9/2022 Should we use repository's "findById()" or service's "getById()" ?
        Optional<Task> optional = taskRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        BeanUtils.copyProperties(taskFullDTO, optional.get());
        if (taskFullDTO.getRelations() != null || taskFullDTO.getRelations().isEmpty()) {
            optional.get().setRelations(
                    taskFullDTO.getRelations().stream()
                            .map(relationConvertToEntityService::convertToEntity)
                            .collect(Collectors.toList())
            );
            for (RelationFullDTO relationFullDTO : taskFullDTO.getRelations()) {
                optional.get().getRelations().add(relationConvertToEntityService.convertToEntity(relationFullDTO));
            }
        }
        return new TaskFullDTO(taskRepository.save(optional.get()));
    }
}
