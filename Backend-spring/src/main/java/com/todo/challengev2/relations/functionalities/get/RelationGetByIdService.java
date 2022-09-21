package com.todo.challengev2.relations.functionalities.get;

import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;

import java.util.UUID;

public interface RelationGetByIdService {

    RelationFullDTO get(UUID id);
}
