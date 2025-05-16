package stepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
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
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys("admin123");
    }


    @And("Clicks login button")
    public void click_login() throws InterruptedException {
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
        Thread.sleep(5000);
    }

    @Then("Verify if user successfully logged-in")
    public void verify_login() throws InterruptedException {
        driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed();
        closeDriver();
    }

}
