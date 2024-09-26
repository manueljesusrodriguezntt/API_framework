package com.api.data.model;

import com.mongodb.client.model.Variable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Variables {

    @Id
    private long id;
    private String WebAndroid;
    private String nombre;

    public Variables(){
    }

    public Variables(String WebAndroid, String nombre){
        this.WebAndroid = WebAndroid;
        this.nombre = nombre;
    }

    public long getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getWebAndroid(){
        return WebAndroid;
    }

    public void setWebAndroid(){
        this.WebAndroid = WebAndroid;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(){
        this.nombre = nombre;
    }
}
