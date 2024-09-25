package com.example.service;

import com.example.model.Gestion;
import com.example.repository.GestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionService {

    @Autowired
    private GestionRepository gestionRepository;

    public List<Gestion> getAllGestiones() {
        return gestionRepository.findAll();
    }

    public Optional<Gestion> getGestionById(String id) {
        return gestionRepository.findById(id);
    }

    public Gestion createGestion(Gestion gestion) {
        return gestionRepository.save(gestion);
    }

    public Gestion updateGestion(String id, Gestion gestionDetails) {
        return gestionRepository.findById(id)
                .map(gestion -> {
                    gestion.setNombre(gestionDetails.getNombre());
                    gestion.setDescripcion(gestionDetails.getDescripcion());
                    return gestionRepository.save(gestion);
                })
                .orElseThrow(() -> new RuntimeException("Gestion no encontrada con id: " + id));
    }

    public void deleteGestion(String id) {
        gestionRepository.deleteById(id);
    }
}
