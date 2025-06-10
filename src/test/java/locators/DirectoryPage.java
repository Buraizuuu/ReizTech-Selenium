package locators;

import org.openqa.selenium.By;
import utility.BrowserDriver;

public class DirectoryPage extends BrowserDriver {
    
    public static By btnDirectory = By.xpath("//span[text()='Directory']");
    public static By txtName = By.xpath("//input[@placeholder=\"Type for hints...\"]");
}
