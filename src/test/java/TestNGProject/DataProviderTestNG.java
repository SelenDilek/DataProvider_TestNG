package TestNGProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

//GITHUB A YUKLENDI

@Test
public class DataProviderTestNG {
    WebDriver driver;

    @BeforeClass
    @Parameters({"browser"})
    public void SetUpp(String browser) {
    if(browser.equals("chrome")){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();


    } else if (browser.equals("firefox"))
    {
        System.out.println("--Firefox--");
        WebDriverManager.firefoxdriver().setup();
        driver=new FirefoxDriver();
    }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.get("https://www.facebook.com/");
        String expected= driver.getTitle();
        System.out.println("expected : " + expected);
        String actual = "Facebook – log in or sign up";
        Assert.assertEquals(actual , expected);
}

    @DataProvider
    public  Object[][] Test1(){
        Object facedata[][]= new Object[2][2];

        facedata[0][0]="yildirimselen17@gmail.com";
        facedata[0][1]="0123456789-+";

        facedata[1][0]="emir.emre0123";
        facedata[1][1]="asdfasdas";

        return facedata;
    }

    @Test(dataProvider = "Test1")
    public void test2(String email ,String password) throws InterruptedException{

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.get("https://www.facebook.com/");

        driver.findElement(By.id("email")).sendKeys(email);
        //driver.manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.findElement(By.linkText("Log in")).click();




    }


   /* @AfterTest
    public void tearDown(){

        driver.close();
        driver.quit();
    }
*/


}
