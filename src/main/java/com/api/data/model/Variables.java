package com.api.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Variables {

    @Id
    private ObjectId _id;      // ObjectId de MongoDB almacenado como String
    private boolean android;
    private boolean web;
    private String nombre;
    private String provider;
    private ArrayList<Object> values;




    // Los constructores, getters y setters son generados automáticamente por Lombok
}
