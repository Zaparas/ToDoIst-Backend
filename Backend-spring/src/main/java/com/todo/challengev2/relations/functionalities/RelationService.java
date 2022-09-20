package com.todo.challengev2.relations.functionalities;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;

import java.util.List;
import java.util.UUID;

public interface RelationService {

    Relation convertToEntity(RelationInDTO relationInDTO);

    List<RelationOutDTO> list();

    RelationOutDTO get(UUID id);

    RelationOutDTO create(RelationInDTO relationInDTO);

    RelationOutDTO update(RelationInDTO relationInDTO, UUID id);

    void delete(UUID id);
}
