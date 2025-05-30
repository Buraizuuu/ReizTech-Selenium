package stepDefinition;

import io.cucumber.java.en.*;
import utility.BrowserDriver;

public class LoginPage extends BrowserDriver {

    @Given("User navigates to login page")
    public void navigate_to_page() throws InterruptedException {
        setupDriver();
        openUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("User populates username and password")
    public void enter_username_password() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(locators.LoginPage.usernameInput).sendKeys("Admin");
        Thread.sleep(2000);
        driver.findElement(locators.LoginPage.passwordInput).sendKeys("admin123");
    }

    @And("Clicks login button")
    public void click_login() throws InterruptedException {
        driver.findElement(locators.LoginPage.loginButton).click();
        Thread.sleep(5000);
    }

    @Then("Verify if user successfully logged-in")
    public void verify_login() throws InterruptedException {
        boolean isDisplayed = driver.findElement(locators.LoginPage.dashboardHeader).isDisplayed();
        if (!isDisplayed) {
            throw new AssertionError("Dashboard header not displayed; login may have failed.");
        }
        closeDriver();
    }
}
