package com.todo.challengev2.relations.functionalities.update;

import com.todo.challengev2.relations.Relation;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationRestrictedDTO;
import com.todo.challengev2.relations.utils.dtos.RelationFullDTO;
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

    /**
     * This method updates the values of the properties of a specific entry in the RelationRepository using the
     * target id as a reference point
     * @param id the id of the target object ot be overwritten
     * @param relationInDTO an object with the values to be passed as input to target object
     * @return A relationOutDTO based on the updated version of the target object
     */
    @Override
    public RelationFullDTO updateService(UUID id, RelationRestrictedDTO relationInDTO) {
        Optional<Relation> optional = relationRepository.findById(id);
        if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Relation with id: <" + id + "> not found. Update Failed.");
        return new RelationFullDTO(optional.get());
    }
}
