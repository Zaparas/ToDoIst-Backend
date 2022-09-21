package com.todo.challengev2.relations.functionalities.get;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class RelationGetByIdServiceImpl implements RelationGetByIdService{

    @Autowired
    private RelationRepository relationRepository;

    /**
     * This method recovers a relation using thee target id as a reference point, in RelationOutDTO format/object
     * @param id the target id to be searched for
     * @return A RelationOutDTO object based on the target Relation
     */
    @Override
    public RelationFullDTO get(UUID id) {
        Optional<Relation> optional = relationRepository.findById(id);
        if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Entity - Relation with id: "+id+" not found.");
        return new RelationFullDTO(optional.get());
    }
}
