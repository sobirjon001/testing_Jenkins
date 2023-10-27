package utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Config {

  private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
  private static final Map<String, String> envValues = new HashMap<>();

  static {
    String[] lines = dotenv.get("ENV").split("\\n");
    for (String line : lines) {
      line = line.replaceAll("\\s", "");
      if (!line.startsWith("#") && line.contains("=")) envValues.put(line.split("=")[0], line.split("=")[1]);
    }
  }

  public static String getEnv(String key) {
    return !Objects.equals(dotenv.get(key), null) ? dotenv.get(key) : envValues.get(key);
  }

}
