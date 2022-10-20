package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputLogin(String login) {
        //clear cookies
        WebElement loginField = driver.findElement(By.xpath("//input[@id='userEmail' and @name='login[email_phone]']"));
        loginField.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        //clear cookies
        WebElement passwdField = driver.findElement(By.xpath("//input[@id='userPass']"));
        passwdField.clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwdField.sendKeys(passwd);
    }

    public void clickLoginButton() {
        WebElement loginBtn = (new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='se_userLogin' and contains(@class, 'login-button')]"))));
        loginBtn.click();
    }

}
