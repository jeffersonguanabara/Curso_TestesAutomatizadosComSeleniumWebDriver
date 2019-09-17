import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestaGoogle {

    private WebDriver driver;

    @Before
    public void inicializando() {
        System.setProperty("webdriver.chrome.driver", "/home/guanabara/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");
    }
    @Test
    public void teste() {
        Assert.assertEquals("Google", driver.getTitle());
    }

    @After
    public void finalizando() {
        driver.quit();
    }
}
