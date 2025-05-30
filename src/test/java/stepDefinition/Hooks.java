package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.BrowserDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks extends BrowserDriver {

    @Before
    public void beforeScenario() {
        setupDriver();
        System.out.println("Browser launched before scenario.");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;

            // Attach screenshot bytes to Cucumber report
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");

            // Format timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy_hhmma")).toUpperCase();

            // Format scenario name for file (replace spaces and special chars with
            // underscores)
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

            // Compose screenshot file name: DateTime_Scenario_Fail_Report.png
            String screenshotName = "screenshots/" + timestamp + "_" + scenarioName + "_Fail_Report.png";

            try {
                File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(screenshotFile.toPath(), Paths.get(screenshotName));
                System.out.println("Saved screenshot: " + screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        closeDriver();
        System.out.println("Browser closed after scenario.");
    }

}
