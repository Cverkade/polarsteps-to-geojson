package com.cverkade.polarstepstogeojson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    public static String readData() throws IOException{
        String polarStepsData = new String(Files.readAllBytes((Paths.get(System.getenv("PATH_TO_FILE")))));
        return polarStepsData;
    }
}
