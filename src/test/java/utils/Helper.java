package utils;

public class Helper {

  public void openURL(String URL) {
    Driver.getDriver().get(URL);
  }

  public String getTitle() {
    return Driver.getDriver().getTitle();
  }
}
