package com.api;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.mongodb.client.model.Filters.*;
import org.bson.conversions.Bson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;


public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://nttprobandoapi:Cartuja2024@cluster0.b9wtp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("variables_entorno");
                MongoCollection<Document> collection = database.getCollection("variables");
                database.runCommand(new Document("ping", 1));
                System.out.println("¿Que dispositivo utilizas? [Web(1) o Android(0)]");
                Scanner reader = new Scanner(System.in);
                int n = reader.nextInt();
                //Document doc = collection.find(eq("Variables", "Accessibility")).first();
                Bson filter = Filters.and(Filters.eq("Web_Android",n));
                collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}

