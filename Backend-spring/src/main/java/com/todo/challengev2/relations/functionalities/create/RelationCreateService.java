package com.todo.challengev2.relations.functionalities.create;

import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;

public interface RelationCreateService {

    RelationOutDTO createRelation(RelationInDTO relationInDTO);
}
