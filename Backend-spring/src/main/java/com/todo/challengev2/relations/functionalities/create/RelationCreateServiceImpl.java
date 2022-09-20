package com.todo.challengev2.relations.functionalities.create;

import com.todo.challengev2.relations.functionalities.convertToEntity.RelationConvertToEntityService;
import com.todo.challengev2.relations.functionalities.RelationRepository;
import com.todo.challengev2.relations.utils.dtos.RelationInDTO;
import com.todo.challengev2.relations.utils.dtos.RelationOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationCreateServiceImpl implements RelationCreateService {

    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private RelationConvertToEntityService relationConvertToEntityService;

    /**
     * This method converts a RelationInDTO to a relation object and then saves it using relationRepository
     * @param relationInDTO the object to be saved
     * @return A RelationOutDTO based on the object saved
     */
    @Override
    public RelationOutDTO createRelation(RelationInDTO relationInDTO) {
        return new RelationOutDTO(relationRepository.save(relationConvertToEntityService.convertToEntity(relationInDTO)));
    }
}
