package com.example.cvai.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAIAPI {
    private static final String OPENAI_API_KEY = "${OPENAI_API_KEY}";

    public static void main(String[] args) throws Exception {
        String cvText = "Text from the CV you want to process";
        String response = makeRequest(cvText);
        System.out.println(response);
    }

    public static String makeRequest(String cvText) throws Exception {
        String endpoint = "https://api.openai.com/v1/completions";

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method and headers
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);

        // Enable output and input streams
        connection.setDoOutput(true);

        // Build the request payload
        String payload = "{\"prompt\":\"" + cvText + "\",\"max_tokens\":100,\"temperature\":0.7}";

        // Send the request
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(payload.getBytes());
        }
        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Close the connection
        connection.disconnect();

        return response.toString();
    }
}
