package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    private static final Properties props = new Properties();

    static
    {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties"))
        {
            props.load(fis);
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key)
    {
        return props.getProperty(key);
    }

    public static int getInt(String key)
    {
        return Integer.parseInt(props.getProperty(key));
    }

    public static boolean isHeadless()
    {
        return get("executionType").equalsIgnoreCase("headless");
    }

    public static Environment getEnv()
    {
        return Environment.valueOf(get("env").toUpperCase());
    }
}