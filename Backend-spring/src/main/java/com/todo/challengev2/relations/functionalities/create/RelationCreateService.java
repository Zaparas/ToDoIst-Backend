package com.todo.challengev2.relations.functionalities.create;

import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;

public interface RelationCreateService {

    RelationFullDTO createRelation(RelationRestrictedDTO relationInDTO);
}
