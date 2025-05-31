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
import java.nio.file.Path;
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

            // Attach screenshot to Cucumber report
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");

            // Get project root directory (absolute path)
            String projectRoot = System.getProperty("user.dir");
            System.out.println("Current working dir (project root): " + projectRoot);

            // Define screenshot folder path under target/screenshots inside project root
            Path screenshotDir = Paths.get(projectRoot, "target", "screenshots");

            // Generate filename with timestamp and sanitized scenario name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy_hhmma")).toUpperCase();
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String screenshotFileName = timestamp + "_" + scenarioName + "_Fail_Report.png";

            // Full path to screenshot file
            Path screenshotPath = screenshotDir.resolve(screenshotFileName);

            try {
                // Create directories if they don't exist
                Files.createDirectories(screenshotDir);

                // Take screenshot as file and copy it to target location
                File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
                Files.copy(screenshotFile.toPath(), screenshotPath);

                System.out.println("✔ Screenshot saved to: " + screenshotPath.toString());
            } catch (IOException e) {
                System.err.println("✘ Failed to save screenshot:");
                e.printStackTrace();
            }
        }

        closeDriver();
        System.out.println("Browser closed after scenario.");
    }
}
