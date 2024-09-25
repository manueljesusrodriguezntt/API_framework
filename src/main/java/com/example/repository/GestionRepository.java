package com.example.repository;

import com.example.model.Gestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionRepository extends MongoRepository<Gestion, String> {
}
