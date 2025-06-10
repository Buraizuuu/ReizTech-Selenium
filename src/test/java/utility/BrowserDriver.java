package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriver {
    public static WebDriver driver;
    private static final String BROWSER = "chrome"; // Change this to "firefox" or "edge" as needed

    public static void setupDriver() {
        if (driver == null) {
            boolean isHeadless = false; // 👉 Or fetch from config file

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
                    ChromeOptions chromeOptions = new ChromeOptions();

                    if (isHeadless) {
                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--window-size=1920,1080");

                        // Fake desktop user-agent
                        chromeOptions.addArguments(
                                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
                    }

                    driver = new ChromeDriver(chromeOptions);

                    // Maximize only if NOT headless
                    if (!isHeadless) {
                        driver.manage().window().maximize();
                    }

                    break;
            }

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

    // Optional: Get driver instance anywhere
    public static WebDriver getDriver() {
        return driver;
    }
}
