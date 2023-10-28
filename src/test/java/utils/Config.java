package utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.util.*;

public class Config {

  private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
  private static final Map<String, String> envValues = new HashMap<>();

  static {
    String[] params = dotenv.get("ENV").split("\\s+");
    for (String param : params) {
      if (!param.startsWith("#") && param.contains("=")) envValues.put(param.split("=")[0], param.split("=")[1]);
    }
  }

  public static String getEnv(String key) {
    return !Objects.equals(dotenv.get(key), null) ? dotenv.get(key) : envValues.get(key);
  }

  public static Set<DotenvEntry> getAllEnv() {
    return dotenv.entries();
  }

}
