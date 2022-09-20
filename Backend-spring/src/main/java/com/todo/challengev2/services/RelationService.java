package com.todo.challengev2.services;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.dto.RelationInDTO;
import com.todo.challengev2.dto.RelationOutDTO;

import java.util.List;
import java.util.UUID;

public interface RelationService {

    Relation convertToEntity(RelationInDTO relationInDTO);

    List<RelationOutDTO> list();
}
