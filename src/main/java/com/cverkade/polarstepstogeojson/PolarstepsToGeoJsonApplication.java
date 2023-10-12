package com.cverkade.polarstepstogeojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PolarstepsToGeoJsonApplication {

    public static void main(String[] args) throws IOException {SpringApplication.run(PolarstepsToGeoJsonApplication.class, args);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = new String(Files.readAllBytes((Paths.get("**"))));
        JsonNode jsonData = objectMapper.readTree(jsonInput);

        JsonNode locationsVisited = jsonData.get("all_steps");
        System.out.println(locationsVisited);


        for (JsonNode locationData: locationsVisited) {
            JsonNode locationJson = locationData.get("location");
            System.out.println(locationJson.get("name"));
            System.out.println(locationJson.get("detail"));
            System.out.println(locationJson.get("lat"));
            System.out.println(locationJson.get("lon"));


        }

        System.out.println(locationsVisited);

    }
}
