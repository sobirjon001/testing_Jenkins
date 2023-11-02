package runners.hooks;

import io.cucumber.java.BeforeAll;
import utils.Config;

public class Hooks {

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Config.getEnv(\"TAGS\") = " + Config.getEnv("TAGS"));
  }
}
