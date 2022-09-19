package com.todo.challengev2.services;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.dto.RelationInDTO;
import com.todo.challengev2.dto.RelationOutDTO;
import com.todo.challengev2.repositories.RelationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implements Service Interface, includes the CRUD methods for Relation Entity.
 */
@Service
public class RelationServiceImpl implements RelationService{

    /**
     * Imports JPA repository
     */
    @Autowired
    private RelationRepository relationRepository;

    /**
     * This method converts our input DTO (RelationInDTO) to an Entity, so we can use it on JPA's methods.
     * @param relationInDTO
     * @return
     */
    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        Relation relation = new Relation();
        BeanUtils.copyProperties(relationInDTO, relation);
        return relation;
    }

    /**
     * This method converts to RelationOutDTO and fetches all the relations, stored in our database.
     * @return
     */
    @Override
    public List<RelationOutDTO> list() {
        List<RelationOutDTO> list = new ArrayList<>();
        for (Relation relation : relationRepository.findAll()) {
            list.add(new RelationOutDTO(relation));
        }
        return list;
    }
}
