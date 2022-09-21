package com.todo.challengev2.relations.functionalities.convertToEntity;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;

public interface RelationConvertToEntityService {

    Relation convertToEntity(RelationFullDTO relationInDTO);

    Relation convertToEntity(RelationRestrictedDTO relationRestrictedDTO);

}
