package com.todo.challengev2.tasks.functionalities.convertToEntity;

import com.todo.challengev2.relations.functionalities.convertToEntity.RelationConvertToEntityService;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.tasks.Task;
import com.todo.challengev2.tasks.utils.dtos.TaskInDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskConvertToEntityServiceImpl implements TaskConvertToEntityService{

    @Autowired
    private RelationConvertToEntityService relationConvertToEntityService;

    @Override
    public Task convertToEntity(TaskInDTO taskInDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskInDTO, task);
        if (taskInDTO.getRelations() != null) {
            for (RelationInDTO relationInDTO : taskInDTO.getRelations()) {
                task.getRelations().add(relationConvertToEntityService.convertToEntity(relationInDTO));
            }
        }
        return task;
    }
}
