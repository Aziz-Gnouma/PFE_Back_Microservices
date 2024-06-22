package com.example.cvai.Entity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class KeywordLoader {

    private final ResourceLoader resourceLoader;

    public KeywordLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<String, Integer> loadKeywordWeights() throws IOException {
        Map<String, Integer> keywordWeights = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:keywords.txt");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String keyword = parts[0].trim();
                    int weight = Integer.parseInt(parts[1].trim());
                    keywordWeights.put(keyword, weight);
                }
            }
        }
        return keywordWeights;
    }
}
