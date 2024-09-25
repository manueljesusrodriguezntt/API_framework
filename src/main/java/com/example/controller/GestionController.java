package com.example.controller;

import com.example.model.Gestion;
import com.example.service.GestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gestiones")
public class GestionController {

    @Autowired
    private GestionService gestionService;

    @GetMapping
    public List<Gestion> getAllGestiones() {
        return gestionService.getAllGestiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gestion> getGestionById(@PathVariable String id) {
        return gestionService.getGestionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gestion createGestion(@RequestBody Gestion gestion) {
        return gestionService.createGestion(gestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestion> updateGestion(@PathVariable String id, @RequestBody Gestion gestionDetails) {
        return ResponseEntity.ok(gestionService.updateGestion(id, gestionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestion(@PathVariable String id) {
        gestionService.deleteGestion(id);
        return ResponseEntity.noContent().build();
    }
}
