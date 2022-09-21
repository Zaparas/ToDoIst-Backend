package com.todo.challengev2.tasks.functionalities.convertToEntity;

import com.todo.challengev2.relations.functionalities.convertToEntity.RelationConvertToEntityService;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.utils.dtos.TaskFullDTO;
import com.todo.challengev2.tasks.utils.dtos.TaskRestrictedDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskConvertToEntityServiceImpl implements TaskConvertToEntityService{

    @Autowired
    private RelationConvertToEntityService relationConvertToEntityService;

    @Override
    public Task convertToEntity(TaskFullDTO taskFullDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskFullDTO, task);
        if (taskFullDTO.getRelations() != null) {
            for (RelationFullDTO relationFullDTO : taskFullDTO.getRelations()) {
                task.getRelations().add(relationConvertToEntityService.convertToEntity(relationFullDTO));
            }
        }
        return task;
    }

    @Override
    public Task convertToEntity(TaskRestrictedDTO taskRestrictedDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskRestrictedDTO, task);
        if (taskRestrictedDTO.getRelations() != null) {
            for (RelationRestrictedDTO relationRestrictedDTO : taskRestrictedDTO.getRelations()) {
                task.getRelations().add(relationConvertToEntityService.convertToEntity(relationRestrictedDTO));
            }
        }
        return task;
    }
}
