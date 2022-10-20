package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginedPage {
    public WebDriver driver;
    public LoginedPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeAdvisesAndHints() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement closeAdvertPopup = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Close']")));
        closeAdvertPopup.click();
        wait.until(ExpectedConditions.invisibilityOf(closeAdvertPopup));
    }

    public void openDropDownList(){
        Actions hoverOn = new Actions(driver);
        hoverOn.moveToElement(driver.findElement(By.xpath("//div[@data-testid='qa-user-dropdown']"))).perform();
    }

    public String getUserName() {
        openDropDownList();
        WebElement username = (new WebDriverWait(driver, Duration.ofSeconds(2))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='qa-user-dropdown']/div/div/div")));
        return username.getText();
    }

    public void logout(){
        openDropDownList();
        WebElement logout = (new WebDriverWait(driver, Duration.ofSeconds(2))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='logout-dropdown']//a")));
        logout.click();
    }
}
