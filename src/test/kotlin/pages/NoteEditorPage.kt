package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

// Tela de adicionar carta.
class NoteEditorPage(driver: AndroidDriver) : BasePage(driver) {

    // TODO 1: locator do campo Frente.
    // O editor cria um campo por item do tipo de nota, todos com o MESMO resource-id
    // (veja LOCATORS_CHEATSHEET.md). Use UiSelector com .instance(0).
    @AndroidFindBy(uiAutomator = "")
    private lateinit var frontField: WebElement

    // TODO 2: locator do campo Verso (mesmo resource-id, .instance(1)).
    @AndroidFindBy(uiAutomator = "")
    private lateinit var backField: WebElement

    @AndroidFindBy(id = "com.ichi2.anki:id/action_save")
    private lateinit var saveButton: WebElement

    // TODO 3: preencher a frente e o verso da carta.
    fun fillCard(front: String, back: String) {
    }

    fun save() {
        saveButton.click()
    }

    // Depois de salvar, o editor continua aberto (pronto para a próxima carta).
    // Depois de salvar, o editor reabre o teclado: às vezes o "voltar" só fecha o teclado.
    // Repete o "voltar" até sair do editor.
    fun backToDeckList() {
        WebDriverWait(driver, Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(1))
            .until {
                if (isOpen()) driver.navigate().back()
                !isOpen()
            }
    }

    private fun isOpen() = driver.currentActivity()?.endsWith("NoteEditorActivity") == true
}
