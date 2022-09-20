package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;

public interface RelationConvertToEntityService {

    Relation convertToEntity(RelationInDTO relationInDTO);
}
