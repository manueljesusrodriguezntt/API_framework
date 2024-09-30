package com.api.data.repository;

import com.api.data.model.Variables;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends MongoRepository<Variables, ObjectId> {
    List<Variables> findByNombre(String nombre);
    // Buscar por android y provider
    List<Variables> findByAndroidAndProvider(boolean android, String provider);
    // Buscar por web y provider
    List<Variables> findByWebAndProvider(boolean web, String provider);
}
