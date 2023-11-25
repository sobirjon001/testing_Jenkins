package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helper {

  int explicit_wait_minutes = 1;

  public void openURL(String URL) {
    System.out.println("Browser opening URL '" + URL + "'");
    Driver.getDriver().get(URL);
  }

  public String getTitle() {
    Wait<WebDriver> wait = new WebDriverWait(Driver.getDriver(), Duration.ofMinutes(explicit_wait_minutes));
    wait.until(ExpectedConditions.titleIs("Google"));
    String title = Driver.getDriver().getTitle();
    System.out.println("Title is '" + title + "'");
    return title;
  }
}
