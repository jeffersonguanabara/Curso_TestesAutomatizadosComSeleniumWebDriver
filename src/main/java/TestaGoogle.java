import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestaGoogle {

    @Test
    public void teste() {
//        System.setProperty("webdriver.gecko.driver","/home/guanabara/Downloads/geckodriver");
//        WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "/home/guanabara/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}
