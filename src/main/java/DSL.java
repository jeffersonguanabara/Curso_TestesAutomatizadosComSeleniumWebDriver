import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escrever(String campo_id, String value) {
        obterElemento(campo_id).sendKeys(value);
    }

    public String obterValorCampo(String campo_id) {
        return obterElemento(campo_id).getAttribute("value");
    }

    public void clicarUmaVez(String campo_id) {
        obterElemento(campo_id).click();
    }

    public boolean obterValorSelecionado(String campo_id) {
        return obterElemento(campo_id).isSelected();
    }

    public void clicarSelectCombo(String campo_id, String value) {
        Select select = (Select) criandoUmSelectCombo(campo_id);
        select.selectByValue(value);
    }

    public Object criandoUmSelectCombo(String campo_id) {
        WebElement element = obterElemento(campo_id);
        Select select = new Select(element);
        return select;
    }

    public String obterTextoDoSelectCombo(String campo_id) {
        Select select = (Select) criandoUmSelectCombo(campo_id);
        return select.getFirstSelectedOption().getText();
    }

    public int obterTamanhoDoSelectCombo(String campo_id) {
        Select select = (Select) criandoUmSelectCombo(campo_id);
        List<WebElement> options = select.getOptions();
        return options.size();
    }

    public  boolean verificarCheckboxsSelecionados(String campo_id, String value){
        Select multipleSelectEspostes = (Select) criandoUmSelectCombo(campo_id);

        List<WebElement> options = multipleSelectEspostes.getAllSelectedOptions();
        boolean validador = false;
        for (WebElement option : options) {
            if ((option.getText().equals(value))) {
                validador = true;
            }
        }
        return validador;
    }

    public WebElement obterElemento(String campo_id) {
        WebElement element = driver.findElement(By.id(campo_id));
        return element;
    }

    public String obterTextoClickButton(String campo_id) {
        return obterElemento(campo_id).getAttribute("value");
    }

    public void clicarUmVezUsandoBy(By by) {
        driver.findElement(by).click();
    }

    public String obterTextoUsandoBy(By by) {
        return driver.findElement(by).getText();
    }
}
