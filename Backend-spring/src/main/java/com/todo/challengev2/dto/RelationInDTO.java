package com.todo.challengev2.dto;

import com.todo.challengev2.config.util.RelationType;
import com.todo.challengev2.domain.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RelationInDTO {

    private RelationType relationType;

    private UUID taskId;
}
