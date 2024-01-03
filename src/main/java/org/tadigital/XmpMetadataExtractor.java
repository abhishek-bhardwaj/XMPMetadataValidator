package org.tadigital;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * This class provides methods to extract metadata from an XMP file.
 * The extracted metadata is stored in a Map.
 *
 * @author mohit.kumar
 *
 */

public class XmpMetadataExtractor {

    /**
     * Extracts metadata from an XMP file and stores it in a Map.
     *
     * @param filePath Path to the XMP file.
     * @return A Map containing extracted metadata.
     * Keys represent tag names and values represent tag values.
     */
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
          //  e.printStackTrace();
        }

        return metadataMap;
    }



    public static void main(String[] args) {
        String filePath = "src/main/resources/SampleXMP.xmp";
        Map<String, String> metadataMap = extractMetadata(filePath);



        System.out.println("Metadata from XMP file:");
        for (Map.Entry<String, String> entry : metadataMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


    }
}
