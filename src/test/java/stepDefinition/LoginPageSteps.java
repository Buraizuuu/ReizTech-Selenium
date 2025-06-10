package stepDefinition;

import io.cucumber.java.en.*;
import locators.LoginPage;
import utility.BasePage;
import utility.BrowserDriver;
import utility.ConfigReader;

public class LoginPageSteps extends BrowserDriver {

    private BasePage basePage = new BasePage(driver);

    @Given("User navigates to login page")
    public void navigate_to_page() {
        openUrl(ConfigReader.get("BASE_URL") + "web/index.php/auth/login");
    }

    @When("User populates username and password")
    public void enter_username_password() {
        basePage.type(LoginPage.txtUsername, ConfigReader.get("USERNAME"));
        basePage.type(LoginPage.txtPassword, ConfigReader.get("PASSWORD"));
    }

    @And("Clicks login button")
    public void click_login() {
        basePage.click(LoginPage.btnLogin);
    }

    @Then("Verify if user successfully logged-in")
    public void verify_login() {
        if (!basePage.isVisible(LoginPage.lblHeader)) {
            throw new AssertionError("Dashboard header not displayed; login may have failed.");
        }
    }
}
