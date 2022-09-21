package com.todo.challengev2.relations.utils.dtos;

import com.todo.challengev2.relations.utils.enums.RelationType;
import com.todo.challengev2.relations.Relation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RelationFullDTO {
    private UUID id;
    private RelationType relationType;
    private UUID taskId;
    private String parentName;

    public RelationFullDTO(Relation relation) {
        this.id = relation.getId();
        this.relationType = relation.getRelationType();
        this.taskId = relation.getTask().getId();
        this.parentName = relation.getTask().getName();
    }
}
