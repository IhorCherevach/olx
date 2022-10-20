package testCases;
import classes.Legkovye;
import conf.prop.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Legkovye_avtomobili;
import pages.LoginPage;
import pages.LoginedPage;
import pages.MainPage;

import java.time.Duration;
import java.util.Objects;
import java.util.regex.Matcher;

public class TC2_FindCar {
    public static MainPage mainPage;
    public static Legkovye_avtomobili legkovye_avtomobili;
    public static WebDriver driver;

    @DataProvider(name = "db")
    public static Object[][] dbData() {
        return new Object[][] {
                {"BmW", "7 серія", "1994", "2001"}
        };
    }

    @BeforeClass
    public static void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        legkovye_avtomobili = new Legkovye_avtomobili(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(ConfProperties.getProperty("page"));
        Thread.sleep(2000);
    }

    @Test(dataProvider = "db")
    public void findCarTest(String brand, String model, String yearFrom, String yearTo) throws InterruptedException {
        mainPage.removeCookiesMessage();
        mainPage.getCategoryTransport();
        mainPage.getSubCategoryLegkovyeInCategoryTransport();
        Thread.sleep(2000);
        legkovye_avtomobili.fieldBrand(brand);
        Thread.sleep(3000);
        legkovye_avtomobili.fieldModel(model);
        Thread.sleep(3000);
        legkovye_avtomobili.fieldYearFrom(yearFrom);
        Thread.sleep(2000);
        legkovye_avtomobili.fieldYearTo(yearTo);
        Thread.sleep(2000);
        Legkovye checkAdvert = legkovye_avtomobili.getFirstAdvertLegkovye();
        Assert.assertTrue(checkAdvert.getHead().matches("(?i).*"+brand.toUpperCase()+".*"));
        Assert.assertTrue(Integer.parseInt(checkAdvert.getYear())>=Integer.parseInt(yearFrom), "Error, value is lower than expected");
        Assert.assertTrue(Integer.parseInt(checkAdvert.getYear())<=Integer.parseInt(yearTo), "Error, value is higher than expected");
        Assert.assertTrue(checkAdvert.getModel().matches("(?i).*"+model+".*"));
        System.out.println(checkAdvert.getHead());
        Thread.sleep(10000);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
