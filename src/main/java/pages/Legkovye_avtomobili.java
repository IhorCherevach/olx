package pages;

import classes.Legkovye;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class Legkovye_avtomobili {
    public WebDriver driver;

    public Legkovye_avtomobili(WebDriver driver) {
        this.driver = driver;
    }

    public void fieldBrand(String name){
        WebElement fieldBrand = driver.findElement(By.xpath("//p[text()='Будь-яка категорія']/../div/div"));
        fieldBrand.click();
        WebElement brand = (new WebDriverWait(driver, Duration.ofSeconds(2))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='"+name.toUpperCase()+"']")));
        while(!brand.isDisplayed()){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView (true);", brand);
        }
        brand.click();
    }

    public void fieldModel(String modelName) throws InterruptedException {
        WebElement fieldModel = driver.findElement(By.xpath("//p[text()='Модель']/..//div//div//div"));
        fieldModel.click();
        Thread.sleep(2000);
        WebElement selectModel = (new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='"+modelName+"']"))));
        while(!selectModel.isDisplayed()){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView (true);", selectModel);
        }
        selectModel.click();
    }

    public void fieldYearFrom(String from) throws InterruptedException {
        WebElement yearFrom = driver.findElement(By.xpath("//p[text()='Рік випуску ']/../div/div[1]/div//input"));
        yearFrom.click();
        yearFrom.click();
        yearFrom.sendKeys(from);
    }

    public void fieldYearTo(String to) throws InterruptedException {
        WebElement yearTo = driver.findElement(By.xpath("//p[text()='Рік випуску ']/../div/div[2]/div//input"));
        yearTo.sendKeys(to);
    }

    public Legkovye getFirstAdvertLegkovye() throws InterruptedException {
        WebElement firstAdvert = driver.findElement(By.xpath("//div[@data-cy='l-card'][1]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView (true);", firstAdvert);
        firstAdvert.click();
        Thread.sleep(2000);
        String head = driver.findElement(By.xpath("//h1")).getText();
        WebElement yearMade = driver.findElement(By.xpath("//p[contains(text(),'Рік випуску:')]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView (true);", yearMade);
        String yearLine = Arrays.stream(yearMade.getText().trim().split(" ")).reduce((first, second) -> second).orElse(null);
        //String[] yearArray = yearLine.split(" ");
        //String year = yearArray[yearArray.length-1];
        String model = driver.findElement(By.xpath("//p[contains(text(),'Модель:')]")).getText().trim();
        return new Legkovye(head, yearLine, model);
    }
}
