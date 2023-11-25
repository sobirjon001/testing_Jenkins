package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.Helper;

public class UI_steps {

  Helper helper = new Helper();

  @Given("I open url {string}")
  public void iOpenUrl(String URL) {
    helper.openURL(URL);
  }

  @Then("Validate button {string} displayed")
  public void validateButtonDisplayed(String arg0) {

  }

  @Then("Validate title is {string}")
  public void validateTitleIs(String expectedTitle) {
    String actualTitle = helper.getTitle();
    Assert.assertEquals(expectedTitle, actualTitle);
  }
}
