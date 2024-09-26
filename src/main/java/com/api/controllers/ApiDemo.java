package com.api.controllers;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.Service.VariableService;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

@RestController
@RequestMapping("api")
public class ApiDemo {
    String connectionString = "mongodb+srv://nttprobandoapi:Cartuja2024@cluster0.b9wtp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .serverApi(serverApi)
            .build();

    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("variables_entorno");
    MongoCollection<Document> collection = database.getCollection("variables");

    @Autowired
    private VariableService variableService;

    @GetMapping("/prueba")
    public String prueba(){
        Document doc = collection.find(eq("web", true))
                .first();
        return doc.toJson();
    }

    @GetMapping("/{platform}")
    public String buscar(@PathVariable("platform") String platform) {
        FindIterable<Document> docs = collection.find(eq(platform, true));
        //Document doc = collection.find(eq("web", true)).first();
        StringBuilder resultado = new StringBuilder();
        for (Document doc : docs) {
            resultado.append(doc.toJson()).append("\n");
        }
        return resultado.toString();
    }

    @PostMapping(value = "/platform")
    public ResponseEntity preguntarPlataforma(@RequestBody String platform){
        variableService.environmentVariables(platform);
        return new ResponseEntity(HttpStatus.OK);
    }

}
