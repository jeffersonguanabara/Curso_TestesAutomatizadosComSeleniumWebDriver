import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    private WebDriver driver;

    @Before
    public void inicializa() {
        System.setProperty(propertyWebDriver, driverBrowser);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void testeTextFieldNome() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Thiago");
        Assert.assertEquals("Thiago", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeTextFieldSobreNome() {
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Rodrigues");
        Assert.assertEquals("Rodrigues", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void testeRadioButtonSexo() {

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

    }

    @Test
    public void testeCheckBoxComidas() {

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());

    }

    @Test
    public void testeSelectEscolaridade() {

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(element);
        selectEscolaridade.selectByValue("superior");
        Assert.assertEquals("Superior", selectEscolaridade.getFirstSelectedOption().getText());

    }

    @Test
    public void testeValidarTamanhoSelectEscolaridade() {

        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select selectEscolaridade = new Select(element);
        List<WebElement> options = selectEscolaridade.getOptions();
        Assert.assertEquals(8, options.size());

    }

    @Test
    public void testeMultipleSelectEsportes() {
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

    }

    @Test
    public void testeTextAreaSugestoes() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\nde\nvarias\nlinhas");
        Assert.assertEquals("teste\nde\nvarias\nlinhas", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void testeButtonCliqueMe() {
        WebElement element = driver.findElement(By.id("buttonSimple"));
        element.click();
        Assert.assertEquals("Obrigado!", element.getAttribute("value"));

    }

    @Test
    public void testeLinkVoltar() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

    }

    @After
    public void finaliza() {
        driver.quit();
    }

}
