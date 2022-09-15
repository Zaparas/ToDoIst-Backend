package com.todo.challengev2.domain;

import com.todo.challengev2.config.util.RelationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private RelationType relationType;

    @ManyToOne
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private Task task;

    public Relation(RelationType relationType, Task task) {
        this.relationType = relationType;
        this.task = task;
    }
}
