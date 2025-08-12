package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Utility class for reading test data from JSON files.
 * Supports:
 * - Dot notation access for nested keys (e.g., "user.address.city")
 * - Indexed array access (e.g., "users[0].name")
 * - Flattening complex JSON structures into List<Map<String, String>> for DataProviders
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    // Base path to your test data directory
    private static final String TEST_DATA_BASE_PATH = "src/test/resources/testdata/";

    /**
     * Reads a single value from a JSON file using dot notation and index support.
     *
     * @param fileName JSON file name in /testdata/ (with or without .json extension)
     * @param keyPath  Dot-separated path to the field (e.g. "user.address.city" or "users[0].name")
     * @return String value if found; otherwise null
     */

    public static String getValue(String fileName, String keyPath) {
        try {
            String filePath = getJsonFilePath(fileName);
            logger.info("Reading JSON file from: {}", filePath);

            JsonElement root = JsonParser.parseReader(new FileReader(filePath));
            JsonElement resolved = resolvePath(root, keyPath);

            if (resolved != null && !resolved.isJsonNull()) {
                String value = resolved.getAsString();
                logger.debug("Resolved key '{}' to value '{}'", keyPath, value);
                return value;
            } else {
                logger.warn("Key '{}' not found or is null", keyPath);
                return null;
            }

        } catch (Exception e) {
            logger.error("Error reading key '{}' from JSON '{}'", keyPath, fileName, e);
            return null;
        }
    }

    /**
     * Reads a JSON file (or a specific node inside it) and maps it to a POJO.
     *
     * @param fileName   The JSON file name in /testdata/
     * @param keyPath    The key path to the desired object (e.g., "billingAddress"), or null for root
     * @param targetType The target POJO class
     * @param <T>        The type parameter for the POJO
     * @return Mapped POJO instance
     */
    public static <T> T readJson(String fileName, String keyPath, Class<T> targetType) {
        try {
            String filePath = getJsonFilePath(fileName);
            logger.info("Reading JSON file for POJO mapping from: {}", filePath);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));

            // If a keyPath is provided, navigate to that node
            if (keyPath != null && !keyPath.isEmpty()) {
                String[] keys = keyPath.split("\\.");
                for (String key : keys) {
                    rootNode = rootNode.path(key);
                }
            }

            return mapper.treeToValue(rootNode, targetType);

        } catch (Exception e) {
            logger.error("Error mapping JSON '{}' to POJO for key '{}': {}", fileName, keyPath, e.getMessage(), e);
            return null;
        }
    }


    /**
     * Reads a JSON file and returns its contents as a List of HashMaps.
     * Each map represents a JSON object (record) with flattened keys for nested structures.
     * <p>
     * Supports:
     * - Array of flat objects
     * - Single flat object
     * - Array of nested objects (flattened)
     *
     * @param fileName The name of the JSON file (without ".json")
     * @return List of maps (each map is one test data row)
     */
    public static List<Map<String, String>> getJsonAsListOfMap(String fileName) {
        List<Map<String, String>> records = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String filePath = getJsonFilePath(fileName);
            File file = new File(filePath);
            logger.info("Reading JSON as list of maps from: {}", filePath);

            JsonNode rootNode = mapper.readTree(file);

            // Case 1: Top-level JSON is an array of records
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    Map<String, String> map = new LinkedHashMap<>();
                    flattenJson(node, map, "");
                    records.add(map);
                }
            }
            // Case 2: Top-level JSON is a single object
            else if (rootNode.isObject()) {
                Map<String, String> map = new LinkedHashMap<>();
                flattenJson(rootNode, map, "");
                records.add(map);
            } else {
                logger.warn("Unsupported JSON format in file '{}'", fileName);
            }

        } catch (Exception e) {
            logger.error("Failed to parse JSON file '{}': {}", fileName, e.getMessage(), e);
        }

        return records;
    }


    /**
     * Resolves a dot-separated key path with optional index notation into a JsonElement.
     * Example key path: "users[0].address.city"
     *
     * @param element Root JSON element
     * @param path    Dot-separated path to resolve
     * @return Resolved JsonElement or null
     */
    private static JsonElement resolvePath(JsonElement element, String path) {
        String[] keys = path.split("\\.");

        for (String key : keys) {
            // Match dot or array pattern: "users[0]" or "address"
            Matcher matcher = Pattern.compile("([a-zA-Z0-9_]+)(\\[(\\d+)\\])?").matcher(key);
            if (!matcher.matches()) {
                logger.warn("Invalid path segment: '{}'", key);
                return null;
            }

            String property = matcher.group(1);
            String indexStr = matcher.group(3);

            // Get the object property
            assert element != null;
            if (!element.isJsonObject()) return null;
            element = element.getAsJsonObject().get(property);

            // Get array index if present
            if (indexStr != null && element != null && element.isJsonArray()) {
                int index = Integer.parseInt(indexStr);
                JsonArray array = element.getAsJsonArray();
                if (index >= 0 && index < array.size()) {
                    element = array.get(index);
                } else {
                    logger.warn("Index {} out of bounds for array '{}'", index, property);
                    return null;
                }
            }
        }

        return element;
    }

    /**
     * Flattens a nested JSON structure into a single-level Map using dot notation.
     * Example:
     * Input: { "user": { "name": "John" } }
     * Output: { "user.name": "John" }
     *
     * @param node   The JsonNode to flatten
     * @param map    Output map with flattened keys and values
     * @param prefix Current key prefix during recursion
     */
    private static void flattenJson(JsonNode node, Map<String, String> map, String prefix) {
        if (node.isObject()) {
            // Traverse nested fields
            node.fieldNames().forEachRemaining(field -> {
                JsonNode child = node.get(field);
                flattenJson(child, map, prefix.isEmpty() ? field : prefix + "." + field);
            });
        } else if (node.isArray()) {
            // Traverse indexed array elements
            for (int i = 0; i < node.size(); i++) {
                flattenJson(node.get(i), map, prefix + "[" + i + "]");
            }
        } else if (node.isValueNode()) {
            // Leaf node value
            map.put(prefix, node.asText());
        }
    }


    /**
     * Builds the full path to the JSON file.
     *
     * @param fileName JSON file name (with or without extension)
     * @return Full path string
     */
    private static String getJsonFilePath(String fileName) {
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }
        return Paths.get(TEST_DATA_BASE_PATH, fileName).toString();
    }
}


