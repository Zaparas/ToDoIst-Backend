package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.RelationType;
import com.todo.challengev2.domain.Relation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RelationOutDTO {

    private UUID id;

    private RelationType relationType;

    private String parentName;
    public RelationOutDTO(Relation relation) {
        this.id = relation.getId();
        this.relationType = relation.getRelationType();
        this.parentName = relation.getTask().getName();
    }
}
