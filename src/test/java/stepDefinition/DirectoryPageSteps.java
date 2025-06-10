package stepDefinition;

import io.cucumber.java.en.*;
import locators.DirectoryPage;
import utility.BasePage;
import utility.BrowserDriver;
import utility.FakeDataUtil;

public class DirectoryPageSteps extends BrowserDriver {

    private BasePage basePage = new BasePage(driver);

    @And("User clicks directory menu")
    public void click_directory() {
        basePage.click(DirectoryPage.btnDirectory);
    }

    @Then("Search via name")
    public void search_name() {
        basePage.type(DirectoryPage.txtName, FakeDataUtil.getFirstName());
    }
}
