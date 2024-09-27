package com.api.controllers;

import com.api.data.model.Variables;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.Service.VariableService;
import com.api.data.connectorMongo.ConnectorMongo;

import javax.print.Doc;
import java.util.ArrayList;
import org.json.*;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@RestController
@RequestMapping("api")
public class ApiDemo {

    MongoCollection<Document> collection = ConnectorMongo.connector();

    @Autowired
    private VariableService variableService;

    @GetMapping("/prueba")
    public String prueba(){
        Document doc = collection.find(eq("web", true))
                .first();
        return doc.toJson();
    }

    @GetMapping("/platform/{platform}")
    public JsonNode buscar(@PathVariable("platform") String platform) {
        ArrayList<Document> docs = collection.find(eq(platform, true)).into(new ArrayList<Document>());
        List<Document> docsWithId = new ArrayList<>();
        for (Document doc : docs) {
            doc.put("_id", doc.getObjectId("_id").toString()); // Convertir ObjectId a String
            docsWithId.add(doc);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(docsWithId);
        return jsonNode;
    }

    @PostMapping("/newDocument")
    public ResponseEntity crearVariable(@RequestBody Variables variable){
        variableService.createVariable(variable);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/updateVariable/{id}")
    public ResponseEntity actualizarVariable(@PathVariable String id, @RequestBody Variables variable ){
        ObjectId idobject = new ObjectId(id);
        variableService.updateVariable(idobject,variable);
        return new ResponseEntity(HttpStatus.OK);
    }

    // Eliminar una variable (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariable(@PathVariable String id) {
        ObjectId idobject = new ObjectId(id);
        if (variableService.getVariableById(idobject) != null) {
            variableService.deleteVariable(idobject);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
