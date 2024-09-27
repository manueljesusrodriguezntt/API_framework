package com.api.data.repository;

import com.api.data.model.Variables;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableRepository extends MongoRepository<Variables, ObjectId> {
}
