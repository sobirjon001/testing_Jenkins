package hooks;

import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.Config;
import utils.Driver;

public class Hooks {

  @BeforeAll
  public static void beforeAll() {
    System.out.println("Config.getEnv(\"TAGS\") = " + Config.getEnv("TAGS"));
  }

  @After
  public void after(Scenario scenario) {
    if (Driver.browserInitialized()) {
      if (scenario.isFailed()) {
        System.out.println("scenario failed! Taking screenshot . . .");
      }
      Driver.close();
    }
  }
}
