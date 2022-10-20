package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    public WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
        WebElement login_link = driver.findElement(By.xpath("//a[@id='topLoginLink']"));
        login_link.click();
    }

    public void getCategoryTransport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement transport = driver.findElement(By.xpath("//div[@class='maincategories-list clr']//span[text()='Авто']"));
        transport.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul//a[@href='https://www.olx.ua/uk/transport/legkovye-avtomobili/']")));
    }

    public void getSubCategoryLegkovyeInCategoryTransport() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement subCategoryLegkovye = driver.findElement(By.xpath("//ul//a[@href='https://www.olx.ua/uk/transport/legkovye-avtomobili/']"));
        Thread.sleep(2000);
        subCategoryLegkovye.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ul//a[@href='https://www.olx.ua/uk/transport/legkovye-avtomobili/']")));
    }

    public void removeCookiesMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'cookie-close')]")));
        driver.findElement(By.xpath("//button[contains(@class,'cookie-close')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[contains(@class,'cookie-close')]")));
    }
}