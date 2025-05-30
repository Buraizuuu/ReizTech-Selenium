package stepDefinition;

import io.cucumber.java.en.*;
import locators.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserDriver;
import utility.ConfigReader;
import java.time.Duration;

public class LoginPageSteps extends BrowserDriver {

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("User navigates to login page")
    public void navigate_to_page() {
        openUrl(ConfigReader.get("BASE_URL") + "web/index.php/auth/login");
    }

    @When("User populates username and password")
    public void enter_username_password() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.txtUsername))
                .sendKeys(ConfigReader.get("USERNAME"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.txtPassword))
                .sendKeys(ConfigReader.get("PASSWORD"));
    }

    @And("Clicks login button")
    public void click_login() {
        wait.until(ExpectedConditions.elementToBeClickable(LoginPage.btnLogin)).click();
    }

    @Then("Verify if user successfully logged-in")
    public void verify_login() {
        if (!wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.lblHeader)).isDisplayed()) {
            throw new AssertionError("Dashboard header not displayed; login may have failed.");
        }
    }
}
