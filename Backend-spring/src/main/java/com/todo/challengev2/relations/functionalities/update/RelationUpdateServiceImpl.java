package com.todo.challengev2.relations.functionalities.update;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class RelationUpdateServiceImpl implements RelationUpdateService {

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public RelationOutDTO updateService(UUID id, RelationInDTO relationInDTO) {
        Optional<Relation> optional = relationRepository.findById(id);
        if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Relation with id: <" + id + "> not found. Update Failed.");
        return new RelationOutDTO(optional.get());
    }
}
