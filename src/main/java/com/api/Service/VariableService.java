package com.api.Service;


import com.api.data.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.data.model.Variables;

import java.util.List;

@Service
public class VariableService {
    @Autowired
    VariableRepository repository;

    public static String environmentVariables(String plataforma){
        return plataforma;
    }

}
