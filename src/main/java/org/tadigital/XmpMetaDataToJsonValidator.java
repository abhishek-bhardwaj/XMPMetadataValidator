package org.tadigital;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class provides methods to compare extracted xmp metadata to external JSON File
 *
 * @author abhishek.bhardwaj
 *
 */


public class XmpMetaDataToJsonValidator {

    public static Map<String, String> extractMetadata(String filePath) {
        Map<String, String> metadataMap = new HashMap<>();
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String nodeName = node.getNodeName();
                    String nodeValue = node.getTextContent().trim();
                    if (!nodeName.isEmpty() && !nodeValue.isEmpty()) {
                        metadataMap.put(nodeName, nodeValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metadataMap;
    }

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
            System.out.println("\nKey existence in JSON file:");
            for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
                String key = entry.getKey();
                boolean keyExists = jsonObject.containsKey(key);
                System.out.println(key + " exists in JSON file: " + keyExists);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
