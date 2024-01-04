package org.tadigital;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Map;


import static org.tadigital.XmpMetadataExtractor.extractMetadata;

/**
 * This class provides methods to compare extracted xmp metadata to external JSON File
 *
 * @author abhishek.bhardwaj
 *
 */


public class XmpMetaDataToJsonValidator {





    public static void main(String[] args) {
        String filePath = "src/main/resources/SampleXMP.xmp";
        Map<String, String> metadataMap = extractMetadata(filePath);

        // Load JSON file
        String jsonFilePath = "src/main/resources/sample.json"; 
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Print Metadata from XMP file
            System.out.println("Metadata from XMP file:");
            for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // Print JSON
            System.out.println("\nJSON from external file:");
            System.out.println(jsonObject.toJSONString());

            // Check if each metadata key exists in the JSON file
          /*  System.out.println("\nKey existence in JSON file:");
            for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
                String key = entry.getKey();
                boolean keyExists = jsonObject.containsKey(key);
                System.out.println(key + " exists in JSON file: " + keyExists);
            }

            */

            System.out.println("\nKey existence in JSON file:");
            for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
                String key = entry.getKey();
                boolean keyExists = jsonObject.keySet().stream().anyMatch(jsonKey -> jsonKey.toString().endsWith(key) || jsonKey.toString().endsWith("}" + key));
                System.out.println(key + " exists in JSON file: " + keyExists);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
