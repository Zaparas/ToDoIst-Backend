package com.todo.challengev2.services;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.dto.RelationInDTO;
import com.todo.challengev2.dto.RelationOutDTO;
import com.todo.challengev2.repositories.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RelationServiceImpl implements RelationService{

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        return null;
    }

    @Override
    public List<RelationOutDTO> list() {
        List<RelationOutDTO> list = new ArrayList<>();
        for (Relation relation : relationRepository.findAll()) {
            list.add(new RelationOutDTO(relation));
        }
        return list;
    }

    @Override
    public RelationOutDTO get(UUID id) {
        return null;
    }

    @Override
    public RelationOutDTO create(RelationInDTO relationInDTO) {
        return null;
    }

    @Override
    public RelationOutDTO update(RelationInDTO relationInDTO, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
