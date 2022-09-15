package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.RelationType;
import com.todo.challengev2.domain.Relation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RelationDTO {

    private UUID id;

    private RelationType relationType;

    public RelationDTO(Relation relation) {
        BeanUtils.copyProperties(this, relation);
    }
}
