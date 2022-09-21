package com.todo.challengev2.relations.utils.dtos;

import com.todo.challengev2.relations.utils.enums.RelationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RelationRestrictedDTO {

    private RelationType relationType;

    private UUID taskId;
}
