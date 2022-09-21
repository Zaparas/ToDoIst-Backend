package com.todo.challengev2.relations.functionalities.update;

import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;

import java.util.UUID;

public interface RelationUpdateService {

    RelationFullDTO updateService(UUID id, RelationRestrictedDTO relationInDTO);
}
