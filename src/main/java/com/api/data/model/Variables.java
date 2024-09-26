package com.api.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Variables {

    @Id
    private String _id;      // MongoDB's ObjectId stored as a String
    private boolean android;
    private boolean web;
    private String nombre;
    private String provider;

    // Default constructor
    public Variables() {}

    // Constructor with all fields
    public Variables(String _id, boolean android, boolean web, String nombre, String provider) {
        this._id = _id;
        this.android = android;
        this.web = web;
        this.nombre = nombre;
        this.provider = provider;
    }

    // Getter and Setter for _id
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    // Getter and Setter for android
    public boolean isAndroid() {
        return android;
    }

    public void setAndroid(boolean android) {
        this.android = android;
    }

    // Getter and Setter for web
    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    // Getter and Setter for nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter and Setter for provider
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
