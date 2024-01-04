package org.tadigital;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ApiToJsonExtractor {

    public static void main(String[] args) {
        // Replace this URL with the actual API endpoint
        String apiUrl = "https://api.publicapis.org/entries";

        // Replace this path with the desired folder path
        String folderPath = "src/main/resources/";

        try {
            // Make a GET request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the JSON response
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                StringBuilder jsonData = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonData.append(line);
                }

                // Create the folder if it doesn't exist
                Path folder = Paths.get(folderPath);
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }

                // Save the JSON data to a file in the specified folder
                String fileName = "data.json";
                Path filePath = Paths.get(folderPath, fileName);
                FileWriter fileWriter = new FileWriter(filePath.toString());

                try {
                    fileWriter.write(jsonData.toString());
                } finally {
                    fileWriter.close();
                }

                System.out.println("JSON data saved successfully to: " + filePath.toString());

            } finally {
                reader.close();
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
