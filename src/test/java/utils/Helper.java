package utils;

public class Helper {

  public void openURL(String URL) {
    System.out.println("Browser opening URL '" + URL + "'");
    Driver.getDriver().get(URL);
  }

  public String getTitle() {
    String title = Driver.getDriver().getTitle();
    System.out.println("Title is '" + title + "'");
    return title;
  }
}
