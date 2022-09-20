package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RelationConvertToEntityServiceImpl implements RelationConvertToEntityService {

    /**
     * Converts a RelationInDTO to a relation object
     * @param relationInDTO the target object to be converted
     * @return a relation object based on the input
     */
    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        Relation target = new Relation();
        BeanUtils.copyProperties(relationInDTO,target);
        return target;
    }
}
