package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

    // Convert Java Object to JSON (Serialization)
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing object: " + e.getMessage());
        }
    }

    // Convert JSON to Java Object (Deserialization)
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing JSON: " + e.getMessage());
        }
    }
}
