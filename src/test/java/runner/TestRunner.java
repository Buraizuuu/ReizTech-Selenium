package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "utility", "stepDefinition" }, plugin = {
        "pretty",
        "html:target/report/cucumber-html-report.html",
// "json:target/report/cucumber.json"
})
public class TestRunner {

    @AfterClass
    public static void renameReportWithDateTime() {
        try {
            // Format: MMddyyyy_hhmma (e.g., 05302025_0751PM)
            String dateTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("MMddyyyy_hhmma"));

            Path source = Path.of("target/report/cucumber-html-report.html");
            Path target = Path.of("target/report/" + dateTime + "_Cucumber_Report.html");

            if (Files.exists(source)) {
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✔ Report renamed to: " + target.toString());
            } else {
                System.out.println("✘ Report file not found to rename.");
            }
        } catch (Exception e) {
            System.err.println("✘ Error while renaming the report:");
            e.printStackTrace();
        }
    }
}
