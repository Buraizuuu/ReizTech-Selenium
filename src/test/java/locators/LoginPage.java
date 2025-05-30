package locators;

import org.openqa.selenium.By;

public class LoginPage {
    public static By txtUsername = By.xpath("//input[@name='username']");
    public static By txtPassword = By.xpath("(//input[@name='password'])[1]");
    public static By btnLogin = By.xpath("(//button[@type='submit'])[1]");
    public static By lblHeader = By.xpath("//h6[text()='Dashboard']");
}
