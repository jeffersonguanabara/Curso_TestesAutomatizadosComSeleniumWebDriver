import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaAlerts {

    static String url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
    static String propertyWebDriver = "webdriver.gecko.driver";
    static String driverBrowser = "src/main/resources/arquivos/geckodriver";

    @Test
    public void testaAlertSimples() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);

        driver.findElement(By.id("alert")).click();
        Alert alertSimples = driver.switchTo().alert();
        Assert.assertEquals("Alert Simples", alertSimples.getText());
        alertSimples.accept();
    }

    @Test
    public void testaAlertConfirm() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);

        driver.findElement(By.id("confirm")).click();
        Alert alertConfirm = driver.switchTo().alert();
        alertConfirm.accept();
        Assert.assertEquals("Confirmado", alertConfirm.getText());
        alertConfirm.accept();

        driver.findElement(By.id("confirm")).click();
        alertConfirm.dismiss();
        Assert.assertEquals("Negado", alertConfirm.getText());
        alertConfirm.accept();
        driver.quit();
    }

    @Test
    public void testaAlertPrompt() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);

        driver.findElement(By.id("prompt")).click();
        Alert alertPrompt = driver.switchTo().alert();
        alertPrompt.sendKeys("12");
        alertPrompt.accept();
        Assert.assertEquals("Era 12?", alertPrompt.getText());
        alertPrompt.accept();
        Assert.assertEquals(":D", alertPrompt.getText());
        alertPrompt.accept();

        driver.quit();
    }
}
