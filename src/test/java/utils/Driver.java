package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public class Driver {

  private static WebDriver driver;
  private static final String browser = "chrome";


  public static WebDriver getDriver() {

    if (driver == null) {
      String browserLocation = Config.getEnv("BROWSER").split(":")[0];
      String browserType = Config.getEnv("BROWSER").split(":")[1];
      try {
        if (Objects.equals(browserLocation, "local")) {
          System.out.println("Getting local driver");
          switch (browserType) {
            case "firefox":
              WebDriverManager.firefoxdriver().setup();
              driver = new FirefoxDriver();
              break;
            case "chrome":
            default:
              WebDriverManager.chromedriver().setup();
              driver = new ChromeDriver();
              break;
          }
        } else {
          System.out.println("Getting remote browser");
          URL url = new URL("http://host.docker.internal:4444/wd/hub");
//                        URL url = new URL("http://localhost:4444/wd/hub");
//        URL url = new URL("http://192.168.1.90:4444/wd/hub");
          DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
          desiredCapabilities.setBrowserName(browserType);
          driver = new RemoteWebDriver(url, desiredCapabilities);
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
    return driver;
  }

  public static void close() {
    if (driver != null) {
      driver.close();
      driver = null;
    }
  }

  public static boolean browserInitialized() {
    return driver != null;
  }
}
