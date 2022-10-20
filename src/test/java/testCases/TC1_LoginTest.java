package testCases;

import conf.prop.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginedPage;
import pages.MainPage;
import java.time.Duration;

public class TC1_LoginTest {
    public static LoginPage loginPage;
    public static LoginedPage loginedPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        loginedPage = new LoginedPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2000));
        driver.get(ConfProperties.getProperty("page"));
    }

    @Test
    public void loginTest() {
        try{
            mainPage.goToLoginPage();
            loginPage.inputLogin(ConfProperties.getProperty("login"));
            loginPage.inputPasswd(ConfProperties.getProperty("password"));
            loginPage.clickLoginButton();
            loginedPage.removeAdvisesAndHints();
            String user = loginedPage.getUserName();
            Assert.assertEquals(ConfProperties.getProperty("username"), user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        loginedPage.logout();
        driver.quit();
    }
}
