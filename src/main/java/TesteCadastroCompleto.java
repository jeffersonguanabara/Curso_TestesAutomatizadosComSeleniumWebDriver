import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastroCompleto {

    static String propertyWebDriver;
    static String driverBrowser;
    static String url;
    //static WebDriver driver;


    public TesteCadastroCompleto() {
        propertyWebDriver = "webdriver.gecko.driver";
        driverBrowser = "src/main/resources/arquivos/geckodriver";
        url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
        //driver = new FirefoxDriver();
    }

    @Test
    public void testeCadastro() {
        System.setProperty(propertyWebDriver, driverBrowser);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);

        // preenchendo o nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
        // preenchendo o sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Calixto");
        // preenchendo o sexo
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        // preenchendo o campo comida
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        // capturando o select de escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        // definindo um select passando um webelement por parametro
        Select selectEscolaridade = new Select(element);
        // preenchendo a escolaridade
        selectEscolaridade.selectByValue("superior");
        // capturando o multi select de esportes
        WebElement esporte = driver.findElement(By.id("elementosForm:esportes"));
        // definindo um select passando um webelement por parametro
        Select selectEsportes = new Select(esporte);
        selectEsportes.selectByValue("futebol");
        // preenchendo o campo sujestoes
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
        // clicando no botao cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assert.assertEquals("Cadastrado!", driver.findElement(By.xpath("//*[@id=\"resultado\"]/span")).getText());
        Assert.assertEquals("Paulo", driver.findElement(By.xpath("//*[@id=\"descNome\"]/span")).getText());
        Assert.assertEquals("Calixto", driver.findElement(By.xpath("//*[@id=\"descSobrenome\"]/span")).getText());
        Assert.assertEquals("Masculino", driver.findElement(By.xpath("//*[@id=\"descSexo\"]/span")).getText());
        Assert.assertEquals("Pizza", driver.findElement(By.xpath("//*[@id=\"descComida\"]/span")).getText());
        Assert.assertEquals("superior", driver.findElement(By.xpath("//*[@id=\"descEscolaridade\"]/span")).getText());
        Assert.assertEquals("Futebol", driver.findElement(By.xpath("//*[@id=\"descEsportes\"]/span")).getText());
        Assert.assertEquals("teste", driver.findElement(By.xpath("//*[@id=\"descSugestoes\"]/span")).getText());

        driver.quit();

    }
}
