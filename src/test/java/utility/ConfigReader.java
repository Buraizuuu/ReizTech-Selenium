package utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigReader {

    private static Map<String, String> configMap;

    static {
        try {
            // Full relative path from project root to config.json inside src/test/java/resources
            String path = "src/test/java/resources/config/config.json";

            // Read all bytes from file into byte[]
            byte[] jsonData = Files.readAllBytes(Paths.get(path));

            // Create Jackson ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Convert JSON bytes to Map<String,String>
            configMap = mapper.readValue(jsonData, new TypeReference<Map<String, String>>() {});

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.json");
        }
    }

    // Get value by key from loaded config
    public static String get(String key) {
        return configMap.get(key);
    }
}
