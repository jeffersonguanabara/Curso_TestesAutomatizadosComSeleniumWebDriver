import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaFramesEJanelas {

    static String propertyDriverBrowser = "webdriver.gecko.driver";
    static String driverBrowser = "src/main/resources/arquivos/geckodriver";
    static String url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
    private WebDriver driver;

    @Before
    public void inicializando() {
        System.setProperty(propertyDriverBrowser, driverBrowser);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void testaFrameOk() {

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        Assert.assertEquals("Frame OK!", texto);
        alerta.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);

    }

    @Test
    public void testaJanelaPopUp() {

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");

        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");

    }

    @Test
    public void testaJanelaPopUpSemTitulo() {

        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");

    }

    @After
    public void finalizando() {
        driver.quit();
    }

}
