package com.todo.challengev2.services;

import com.todo.challengev2.domain.Relation;
import com.todo.challengev2.dto.RelationInDTO;
import com.todo.challengev2.dto.RelationOutDTO;
import com.todo.challengev2.repositories.RelationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Relation target = new Relation();
        BeanUtils.copyProperties(relationInDTO,target);
        return target;
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
        Optional<Relation> optional = relationRepository.findById(id);
        if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Entity - Relation with id: "+id+" not found.");
        return new RelationOutDTO(optional.get());
    }

    @Override
    public RelationOutDTO create(RelationInDTO relationInDTO) {
        return new RelationOutDTO(relationRepository.save(convertToEntity(relationInDTO)));
    }

    @Override
    public RelationOutDTO update(RelationInDTO relationInDTO, UUID id) {
        Optional<Relation> optional = relationRepository.findById(id);
        if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Relation with id: <" + id + "> not found. Update Failed.");
        return new RelationOutDTO(optional.get());
    }

    @Override
    public void delete(UUID id) {
        if (get(id)!=null) relationRepository.delete(relationRepository.findById(id).get());
    }
}
