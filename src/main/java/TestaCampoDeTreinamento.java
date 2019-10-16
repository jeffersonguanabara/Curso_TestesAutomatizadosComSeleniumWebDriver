import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestaCampoDeTreinamento {

    static String url = String.format("file:///%s/src/main/resources/paginas/componentes.html", System.getProperty("user.dir"));
    static String propertyWebDriver = "webdriver.gecko.driver";
    static String driverBrowser = "src/main/resources/arquivos/geckodriver";
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        System.setProperty(propertyWebDriver, driverBrowser);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        dsl = new DSL(driver);
    }

    @Test
    public void testeTextFieldNome() {
        dsl.escrever("elementosForm:nome", "Thiago");
        Assert.assertEquals("Thiago", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeTextFieldSobreNome() {
        dsl.escrever("elementosForm:sobrenome", "Rodrigues");
        Assert.assertEquals("Rodrigues", dsl.obterValorCampo("elementosForm:sobrenome"));
    }

    @Test
    public void testeRadioButtonSexo() {
        dsl.clicarUmaVez("elementosForm:sexo:0");
        Assert.assertTrue(dsl.obterValorSelecionado("elementosForm:sexo:0"));

    }

    @Test
    public void testeCheckBoxComidas() {
        dsl.clicarUmaVez("elementosForm:comidaFavorita:0");
        Assert.assertTrue(dsl.obterValorSelecionado("elementosForm:comidaFavorita:0"));
    }

    @Test
    public void testeSelectEscolaridade() {
        dsl.clicarSelectCombo("elementosForm:escolaridade", "superior");
        Assert.assertEquals("Superior", dsl.obterTextoDoSelectCombo("elementosForm:escolaridade"));
    }

    @Test
    public void testeValidarTamanhoSelectEscolaridade() {
        dsl.obterTamanhoDoSelectCombo("elementosForm:escolaridade");
        Assert.assertEquals(8, dsl.obterTamanhoDoSelectCombo("elementosForm:escolaridade"));
    }

    @Test
    public void testeMultipleSelectEsportes() {
        dsl.clicarSelectCombo("elementosForm:esportes", "natacao");
        dsl.clicarSelectCombo("elementosForm:esportes", "futebol");
        dsl.clicarSelectCombo("elementosForm:esportes", "Karate");
        Assert.assertEquals(true, dsl. verificarCheckboxsSelecionados("elementosForm:esportes", "Natacao"));
        Assert.assertEquals(true, dsl. verificarCheckboxsSelecionados("elementosForm:esportes", "Futebol"));
        Assert.assertEquals(true, dsl. verificarCheckboxsSelecionados("elementosForm:esportes", "Karate"));
    }

    @Test
    public void testeTextAreaSugestoes() {
        dsl.escrever("elementosForm:sugestoes", "teste\nde\nvarias\nlinhas");
        Assert.assertEquals("teste\nde\nvarias\nlinhas", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testeButtonCliqueMe() {
        dsl.clicarUmaVez("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterTextoClickButton("buttonSimple"));

    }

    @Test
    public void testeLinkVoltar() {
        dsl.clicarUmVezUsandoBy(By.linkText("Voltar"));
        Assert.assertEquals("Voltou!", dsl.obterTextoUsandoBy(By.id("resultado")));

    }

    @After
    public void finaliza() {
        driver.quit();
    }

}
