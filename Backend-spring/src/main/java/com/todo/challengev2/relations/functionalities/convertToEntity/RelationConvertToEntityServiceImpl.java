package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RelationConvertToEntityServiceImpl implements RelationConvertToEntityService {

    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        Relation target = new Relation();
        BeanUtils.copyProperties(relationInDTO,target);
        return target;
    }
}
