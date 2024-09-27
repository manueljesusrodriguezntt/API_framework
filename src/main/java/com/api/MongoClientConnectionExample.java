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
import org.bson.conversions.Bson;
import java.util.Scanner;

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
                System.out.println("¿Qué dispositivo utilizas? [web o android]?");
                Scanner reader = new Scanner(System.in);
                String deviceType = reader.nextLine().trim().toLowerCase();
                Bson filter;
                if ("web".equals(deviceType)) {
                    filter = Filters.eq("web", true);
                } else if ("android".equals(deviceType)) {
                    filter = Filters.eq("android", true);
                } else {
                    System.out.println("Entrada no válida. Por favor, introduce 'web' o 'android'.");
                    reader.close();
                    return;
                }
                collection.find(filter).forEach(doc -> System.out.println(doc.toJson()));
                reader.close();
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
