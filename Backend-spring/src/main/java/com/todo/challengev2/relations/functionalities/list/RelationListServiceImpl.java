package com.todo.challengev2.relations.functionalities.list;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelationListServiceImpl implements RelationListService{

    @Autowired
    private RelationRepository relationRepository;

    /**
     * This method returns a list of RelationOutDTO objects based the Relation objects currently stored in the
     * RelationRepository
     * @return List of all Relation object in the RelationRepository as RelationOutDTO objects
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
