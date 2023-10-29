package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import utils.Config;

public class testSteps {
  @Given("I print env variable {string}")
  public void iPrintEnvVariable(String envVariableKey) {
    System.out.println(envVariableKey + " = " + Config.getEnv(envVariableKey));
    Assert.assertTrue(true);
  }

  @Given("I print all env variables")
  public void iPrintAllEnvVariables() {
    System.out.println(Config.getEnvValues());
    Assert.assertTrue("FAILED!", false);
  }
}
