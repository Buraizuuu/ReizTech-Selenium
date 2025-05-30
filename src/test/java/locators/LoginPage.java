package locators;
import org.openqa.selenium.By;

public class LoginPage {
        public static By usernameInput = By.xpath("//input[@name='username']");
    public static By passwordInput = By.xpath("(//input[@name='password'])[1]");
    public static By loginButton = By.xpath("(//button[@type='submit'])[1]");
    public static By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
}
