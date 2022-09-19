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
import java.util.Optional;
import java.util.UUID;

@Service
public class RelationServiceImpl implements RelationService{

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public Relation convertToEntity(RelationInDTO relationInDTO) {
        Relation relation = new Relation();
        BeanUtils.copyProperties(relationInDTO, relation);
        return relation;
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
        Optional<Relation> optionalRelation = relationRepository.findById(id);
        if (optionalRelation.isEmpty()) {
            throw new RuntimeException("Did not found relation with id: " + id);
        }
        return new RelationOutDTO(optionalRelation.get());
    }

    @Override
    public RelationOutDTO create(RelationInDTO relationInDTO) {
        return new RelationOutDTO(relationRepository.save(convertToEntity(relationInDTO)));
    }

    @Override
    public RelationOutDTO update(RelationInDTO relationInDTO, UUID id) {
        Optional<Relation> optionalRelation = relationRepository.findById(id);
        if (optionalRelation.isEmpty()) {
            throw new RuntimeException("Did not found relation with id: " + id);
        }
        BeanUtils.copyProperties(optionalRelation.get(), relationInDTO);
        return new RelationOutDTO(relationRepository.save(optionalRelation.get()));
    }

    @Override
    public void delete(UUID id) {
        if (get(id) != null) {
            relationRepository.deleteById(id);
        }
    }
}
