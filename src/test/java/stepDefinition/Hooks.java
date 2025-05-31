package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utility.BrowserDriver;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Hooks extends BrowserDriver {

    @Before
    public void beforeScenario() {
        setupDriver();
        System.out.println("Browser launched before scenario.");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot and attach to Cucumber report
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
            System.out.println("✔ Screenshot attached to Cucumber report.");

            /*
            // 💤 Original file-saving logic (commented out for simplicity)
            String projectRoot = System.getProperty("user.dir");
            System.out.println("Current working dir (project root): " + projectRoot);

            Path screenshotDir = Paths.get(projectRoot, "target", "screenshots");

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy_hhmma")).toUpperCase();
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String screenshotFileName = timestamp + "_" + scenarioName + "_Fail_Report.png";

            Path screenshotPath = screenshotDir.resolve(screenshotFileName);

            try {
                Files.createDirectories(screenshotDir);

                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(screenshotFile.toPath(), screenshotPath);

                System.out.println("✔ Screenshot saved to: " + screenshotPath.toString());
            } catch (IOException e) {
                System.err.println("✘ Failed to save screenshot:");
                e.printStackTrace();
            }
            */
        }

        closeDriver();
        System.out.println("Browser closed after scenario.");
    }
}
