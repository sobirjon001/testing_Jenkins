package stepDefinitions;

import io.cucumber.java.en.Given;
import utils.Config;

public class testSteps {
  @Given("I print env variable {string}")
  public void iPrintEnvVariable(String envVariableKey) {
    System.out.println(envVariableKey + " = " + Config.getEnv(envVariableKey));
  }
}
