package com.cverkade.polarstepstogeojson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PolarstepsToGeoJsonApplication {

    public static void main(String[] args) throws IOException {SpringApplication.run(PolarstepsToGeoJsonApplication.class, args);
        ObjectMapper objectMapper = new ObjectMapper();
        String polarStepsData = new String(Files.readAllBytes((Paths.get(System.getenv("PATH_TO_FILE")))));

        JsonNode jsonData = objectMapper.readTree(polarStepsData);
        JsonNode locationsVisited = jsonData.get("all_steps");

        ObjectNode geoJson = objectMapper.createObjectNode();
        geoJson.put("type", "FeatureCollection");
        ArrayNode features = objectMapper.createArrayNode();
        geoJson.set("features", features);

        for (JsonNode locationData: locationsVisited) {
            JsonNode location = locationData.get("location");

            String name = location.get("name").asText();
            String detail = location.get("detail").asText();
            Double lat = location.get("lat").asDouble();
            Double lon = location.get("lon").asDouble();

            ObjectNode feature = objectMapper.createObjectNode();
            feature.put("type", "Feature");

            ObjectNode geometry = objectMapper.createObjectNode();
            geometry.put("type", "Point");
            ArrayNode coordinates = objectMapper.createArrayNode();
            coordinates.add(lon);
            coordinates.add(lat);
            geometry.set("coordinates", coordinates);
            feature.set("geometry", geometry);

            ObjectNode properties = objectMapper.createObjectNode();
            properties.put("name", name);
            properties.put("detail", detail);
            feature.set("properties", properties);

            features.add(feature);
        }

        System.out.println(geoJson);
    }
}
