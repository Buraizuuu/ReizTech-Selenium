package stepDefinition;

import io.cucumber.java.en.*;
import locators.DirectoryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BrowserDriver;
import utility.FakeDataUtil;

import java.time.Duration;

public class DirectoryPageSteps extends BrowserDriver {

    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @And("User clicks directory menu")
    public void click_directory() {
        wait.until(ExpectedConditions.elementToBeClickable(DirectoryPage.btnDirectory)).click();
    }

    @Then("Search via name")
    public void search_name() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DirectoryPage.txtName))
                .sendKeys(FakeDataUtil.getFirstName());
    }

}
