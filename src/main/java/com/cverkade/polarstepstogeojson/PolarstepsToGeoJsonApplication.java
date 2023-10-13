package com.cverkade.polarstepstogeojson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PolarstepsToGeoJsonApplication {

    public static void main(String[] args) throws IOException {SpringApplication.run(PolarstepsToGeoJsonApplication.class, args);
        String polarStepsData = JsonReader.readData();
        String geoJson = GeoJsonGenerator.getGeoJson(polarStepsData);
        String lineStringGeoJson = GeoJsonGenerator.getLineString(polarStepsData);
        System.out.println(geoJson);
        System.out.println(lineStringGeoJson);
    }
    }
