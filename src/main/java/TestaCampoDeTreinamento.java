import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TestaCampoDeTreinamento {

    static String url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
    static String propertyWebDriver = "webdriver.gecko.driver";
    static String driverBrowser = "src/main/resources/arquivos/geckodriver";

    @Test
    public void testeTextFieldNome() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Thiago");
        Assert.assertEquals("Thiago", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testeTextFieldSobreNome() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Rodrigues");
        Assert.assertEquals("Rodrigues", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testeRadioButtonSexo() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

        driver.quit();
    }

    @Test
    public void testeCheckBoxComidas() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
        driver.quit();
    }

    @Test
    public void testeSelectEscolaridade() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(element);
        selectEscolaridade.selectByValue("superior");
        Assert.assertEquals("Superior", selectEscolaridade.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void testeValidarTamanhoSelectEscolaridade() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(element);
        List<WebElement> options = selectEscolaridade.getOptions();
        Assert.assertEquals(8, options.size());
        driver.quit();
    }

    @Test
    public void testeMultipleSelectEsportes() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select multipleSelectEspostes = new Select(element);
        multipleSelectEspostes.selectByValue("natacao");
        multipleSelectEspostes.selectByValue("futebol");
        multipleSelectEspostes.selectByValue("Karate");

        List<WebElement> options = multipleSelectEspostes.getAllSelectedOptions();
        boolean validador = false;
        for (WebElement option : options) {
            if ((option.getText().equals("Natação")) || (option.getText().equals("Futebol")) || (option.getText().equals("Karate"))) {
                validador = true;
            }
        }

        Assert.assertEquals(true, validador);

        driver.quit();
    }

    @Test
    public void testeTextAreaSugestoes() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\nde\nvarias\nlinhas");
        Assert.assertEquals("teste\nde\nvarias\nlinhas", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testeButtonCliqueMe() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebElement element = driver.findElement(By.id("buttonSimple"));
        element.click();
        Assert.assertEquals("Obrigado!", element.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeLinkVoltar() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

}
