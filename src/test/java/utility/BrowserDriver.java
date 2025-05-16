package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriver {
    public static WebDriver driver;
    private static final String BROWSER = "chrome"; // Change this to "firefox" or "edge" as needed

    public static void setupDriver() {
        if (driver == null) { // Ensure the driver is only initialized once
            switch (BROWSER.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
    }

    public static void openUrl(String url) {
        if (driver == null) {
            setupDriver();
        }
        driver.get(url);
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset driver to null after quitting
        }
    }
}
