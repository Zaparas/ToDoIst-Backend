package com.todo.challengev2.relations.functionalities.update;

import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;

import java.util.UUID;

public interface RelationUpdateService {

    RelationOutDTO updateService(UUID id, RelationInDTO relationInDTO);
}
