import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Wiki {

    String releaseDateWiki,countryNameWiki;
    String releseDateImbd,countryNameImdb;
    WebDriver driver;

    @BeforeMethod
    public void ExtractingData() {

        System.setProperty("webdriver.chrome.driver","C://NewSelenium//Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Pushpa:_The_Rise");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");

        releaseDateWiki = driver.findElement(By.xpath("//tbody/tr[12]/td[1]/div[1]")).getText();
        countryNameWiki = driver.findElement(By.xpath("//td[contains(text(),'India')]")).getText();
        System.out.println("From wiki :  " +releaseDateWiki+ " " +countryNameWiki);

        //Opening Imdb in new tab

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.imdb.com/title/tt9389998/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,5900)");

        releseDateImbd = driver.findElement(By.partialLinkText("December 17, 2021 (U")).getText();
        countryNameImdb = driver.findElement(By.partialLinkText("India")).getText();
        System.out.println("From Imbd : " +releseDateImbd+ " " +countryNameImdb);

    }

    @Test
    public void verify()
    {
        Assert.assertEquals(releaseDateWiki,releseDateImbd);
        Assert.assertEquals(countryNameWiki,countryNameImdb);
    }

    @AfterMethod
    public void closeBroswer()
    {
        driver.quit();
    }


}
