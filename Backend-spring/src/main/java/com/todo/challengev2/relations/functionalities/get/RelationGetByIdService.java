package com.todo.challengev2.relations.functionalities.get;

import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;

import java.util.UUID;

public interface RelationGetByIdService {

    RelationOutDTO get(UUID id);
}
