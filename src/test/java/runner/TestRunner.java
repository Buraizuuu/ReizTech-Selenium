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
        "html:target/report/html/cucumber-html-report.html",
        "json:target/report/json/cucumber.json"
})

public class TestRunner {

    @AfterClass
    public static void renameReportsWithDateTime() {
        try {
            String dateTime = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("MMddyyyy_hhmma"));

            Path htmlSource = Path.of("target/report/html/cucumber-html-report.html");
            Path jsonSource = Path.of("target/report/json/cucumber.json");

            Path htmlTarget = Path.of("target/report/html/" + dateTime + "_Cucumber_Report.html");
            Path jsonTarget = Path.of("target/report/json/" + dateTime + "_Cucumber_Report.json");

            if (Files.exists(htmlSource)) {
                Files.move(htmlSource, htmlTarget, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✔ HTML report renamed to: " + htmlTarget);
            } else {
                System.out.println("✘ HTML report file not found.");
            }

            if (Files.exists(jsonSource)) {
                Files.move(jsonSource, jsonTarget, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✔ JSON report renamed to: " + jsonTarget);
            } else {
                System.out.println("✘ JSON report file not found.");
            }
        } catch (Exception e) {
            System.err.println("✘ Error while renaming reports:");
            e.printStackTrace();
        }
    }

}
