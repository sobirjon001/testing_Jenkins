package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.Config;

import java.io.*;

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

  @Then("I print file {string} content")
  public void iPrintFileContent(String fileName) {
    try {
      File file = new File("src/test/resources/downloads/" + fileName);
      InputStream inputStream = new FileInputStream(file);
      System.out.println("inputStream = " + inputStream);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }


}
