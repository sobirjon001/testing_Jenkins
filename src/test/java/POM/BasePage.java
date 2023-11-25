package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class BasePage {

  public BasePage() {
    PageFactory.initElements(Driver.getDriver(), this);
  }

  public WebElement getButtonByText(String buttonText) {
    return Driver.getDriver().findElement(By.xpath("*[@role='button' && @value='" + buttonText + "']"));
  }

}
