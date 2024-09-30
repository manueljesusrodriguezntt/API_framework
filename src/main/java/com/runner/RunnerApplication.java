package com.runner;
import com.api.data.model.Variables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.api.Application;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

public class RunnerApplication {

    public static void main(String[] args) {
        // Ejecutar la API en un subproceso
        Thread apiThread = new Thread(() -> {
            try {
                // Lanza la API
                Application.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Pregunta sobre dispositivo utilizado y guarda el valor en la variable String deviceType
        System.out.println("¿Qué dispositivo utilizas? [web o android]?");
        Scanner device = new Scanner(System.in);
        String deviceType = device.nextLine().trim().toLowerCase();

        //Pregunta sobre dispositivo utilizado y guarda el valor en la variable String providerType
        System.out.println("¿Qué entorno utilizas? [Local o SauceLabs]?");
        Scanner provider = new Scanner(System.in);
        String providerType = provider.nextLine().trim().toLowerCase();


        apiThread.start(); // Iniciar la API en un subproceso

        // Pausar brevemente para permitir que la API arranque correctamente
        try {
            Thread.sleep(11000); // Ajusta el tiempo de espera según el tiempo de arranque de tu API
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hacer una solicitud HTTP GET a la API
        try {
            URL url = new URL("http://localhost:8080/api/platform/"+deviceType); // Cambia el puerto y endpoint según tu configuración
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");



            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // Habilitar pretty-printing
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

            // Convertir la cadena JSON a JsonNode
            JsonNode jsonContent = objectMapper.readTree(content.toString());

            // Convertir el JsonNode a una cadena JSON formateada
            String formattedJson = writer.writeValueAsString(jsonContent);

            // Imprimir la respuesta formateada
            System.out.println("Respuesta del servidor: \n" + formattedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }



        // Apagar la API una vez que se ha obtenido el resultado
        shutdownAPI();

        System.out.println("API apagada.");
    }

    // Metodo para apagar la API
    public static void shutdownAPI() {
        try {
            URL url = new URL("http://localhost:8080/actuator/shutdown"); // Actuator para apagar
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // Actuator usa POST para shutdown

            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta del apagado: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}