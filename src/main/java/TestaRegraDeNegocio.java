import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestaRegraDeNegocio {

    static String propertyDriver = "webdriver.gecko.driver";
    static String driverBrowser = "src/main/resources/arquivos/geckodriver";
    static String url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
    WebDriver driver = null;

    @Before
    public void inicializando() {
        System.setProperty(propertyDriver, driverBrowser);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void testaValidacaoCampoNome() {
        // dando click no botao cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // trocando o contexto da pagina padrao para o alert
        Alert alert = driver.switchTo().alert();
        // validando a mensagem do alert
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        // clicando no botao ok do alert
        alert.accept();

    }

    @Test
    public void testaValidacaoCampoSobrenome() {
        // preenchendo o campo nome do formulário
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
        // clicando no botao cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // mudando o contexto da pagina padrao para o alert
        Alert alert = driver.switchTo().alert();
        // validando a mensagem do alert
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        // clicando no botao ok do alert
        alert.accept();

    }

    @Test
    public void testaValidacaoCampoComida() throws InterruptedException {
        // preenchendo o campo nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
        // preenchendo o campo sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nobre");
        // preenchendo o campo sexo
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        for (int i = 0; i <= 2; i++) {
            // preenchendo o campo comida
            if (i < 2) {
                driver.findElement(By.id(String.format("elementosForm:comidaFavorita:%s", i))).click();
                driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
            } else {
                driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
                driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
                driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
            }
            // clicando o botao cadastrar
            driver.findElement(By.id("elementosForm:cadastrar")).click();

            // validando a mensagem do alert
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
            alert.accept();
            Thread.sleep(5000);
            driver.findElement(By.id(String.format("elementosForm:comidaFavorita:%s", i))).click();
            driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        }
    }

    @Test
    public void testavalidacaoCampoEsportes() {
        // preenchendo o campo nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Paulo");
        // preenchendo o campo sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Nobre");
        // preenchendo o campo sexo
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        // preenchendo o campo comida
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        // preenchendo o campo escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select escolaridade = new Select(element);
        escolaridade.selectByValue("mestrado");
        // preenchendo o campo esportes
        WebElement esporte = driver.findElement(By.id("elementosForm:esportes"));
        Select esportes = new Select(esporte);
        esportes.selectByValue("futebol");
        esportes.selectByValue("nada");
        // clicando no botao cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        // mudando o contexto da pagina padrao para o alert
        Alert alert = driver.switchTo().alert();
        // validando a mensagem contida no alert
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        // fechando o alert e voltando ao contexto da pagina padrao
        alert.accept();


    }

    @After
    public void finalizando() {
        driver.quit();
    }

}
