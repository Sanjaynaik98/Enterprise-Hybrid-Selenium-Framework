
package com.enterprise.automation.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class JsonDataReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonDataReader() {
    }

    public static List<Map<String, String>> readTestData(
            String resourcePath) {

        try (InputStream input = JsonDataReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath)) {

            if (input == null) {
                throw new IllegalArgumentException(
                        "JSON resource not found: " + resourcePath);
            }

            return MAPPER.readValue(
                    input,
                    new TypeReference<List<Map<String, String>>>() {
                    }
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to read JSON test data: " + resourcePath, e);
        }
    }
}
