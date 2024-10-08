package com.api.Service;

import com.api.data.model.Variables;
import com.api.data.repository.VariableRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariableService {

    @Autowired
    VariableRepository repository;

    public static String environmentVariables(String plataforma){
        return plataforma;
    }

    public Variables createVariable(Variables variable) {
        return repository.save(variable);
    }

    public Variables getVariableById(ObjectId id) {
        return repository.findById(id).orElse(null);
    }

    public List<Variables> getAllVariables() {
        return repository.findAll();
    }

    public Variables updateVariable(ObjectId id, Variables variable) {
        if (repository.existsById(id)) {
            variable.set_id(id);
            return repository.save(variable);
        } else {
            return null;
        }
    }

    public Variables updateVariableByName(String nombre, Variables updatedVariable){
        Variables variablesExistentes = repository.findByNombre(nombre);
        // Si existe al menos una variable en la lista, tomamos la primera

        Variables variableExistente = variablesExistentes;
        updatedVariable.set_id(variableExistente.get_id());    // mantiene el mismo ObjectId
        return repository.save(updatedVariable);   // guarda la variable actualizada
    }

    public void deleteVariable(ObjectId id) {
        repository.deleteById(id);
    }

    public Variables getVariablesByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    public List<Variables> getVariablesByPlatformAndProvider(String platform, String provider) {
        if (platform.equalsIgnoreCase("android")) {
            return repository.findByAndroidAndProvider(true, provider);
        } else if (platform.equalsIgnoreCase("web")) {
            return repository.findByWebAndProvider(true, provider);
        } else {
            throw new IllegalArgumentException("Plataforma no válida: " + platform);
        }
    }

}
