package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public class Driver {

    private static final ThreadLocal<WebDriver> driver_pool = new ThreadLocal<>();


    public static WebDriver getDriver() {

        if (driver_pool.get() == null) {
            String browserLocation = Config.getEnv("BROWSER").split(":")[0];
            String browserType = Config.getEnv("BROWSER").split(":")[1];
            try {
                if (Objects.equals(browserLocation, "local")) {
                    System.out.println("Getting local driver");
                    switch (browserType) {
                        case "firefox":
                            WebDriverManager.firefoxdriver().setup();
                            driver_pool.set(new FirefoxDriver());
                            break;
                        case "chrome":
                        default:
                            WebDriverManager.chromedriver().setup();
                            driver_pool.set(new ChromeDriver());
                            break;
                    }
                } else {
                    String team = Config.getEnv("TEAM");
                    System.out.println("Getting remote browser for " + browserType + " for team " + team);
//                    URL url = new URL("http://host.docker.grid:4444/wd/hub");
//                    URL url = new URL("http://gateway.docker.internal:4444/wd/hub");
//                    URL url = new URL("http://localhost:4444/wd/hub");
                    URL url = new URL("http://" + team + "-selenium-hub:4444/wd/hub");
//                    URL url = new URL("http://192.168.1.217:4444/wd/hub");
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setBrowserName(browserType);
                    driver_pool.set(new RemoteWebDriver(url, desiredCapabilities));
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e.getMessage());
            }
            driver_pool.get().manage().timeouts().implicitlyWait(Duration.ofMinutes(10));
            driver_pool.get().manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));
        }
        return driver_pool.get();

    }

    public static void close() {
        if (driver_pool.get() != null) {
            driver_pool.get().quit();
            driver_pool.remove();
        }
    }

    public static boolean browserInitialized() {
        return driver_pool.get() != null;
    }
}
