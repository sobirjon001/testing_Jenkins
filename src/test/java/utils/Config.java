package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

  private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

  public static String getEnv(String key) {
    return dotenv.get(key);
  }

}
