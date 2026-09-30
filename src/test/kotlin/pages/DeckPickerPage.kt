package pages

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

// Tela inicial do AnkiDroid: a lista de baralhos.
class DeckPickerPage(driver: AndroidDriver) : BasePage(driver) {

    // Botão "+" flutuante. 1º toque abre o menu; com o menu aberto, o mesmo botão abre o editor de nota.
    @AndroidFindBy(id = "com.ichi2.anki:id/fab_main")
    private lateinit var fabButton: WebElement

    @AndroidFindBy(id = "com.ichi2.anki:id/add_deck_button")
    private lateinit var addDeckButton: WebElement

    // Campo de texto do diálogo "Criar baralho"
    @AndroidFindBy(id = "com.ichi2.anki:id/dialog_text_input")
    private lateinit var deckNameInput: WebElement

    // Botão OK do diálogo (id padrão do Android)
    @AndroidFindBy(id = "android:id/button1")
    private lateinit var confirmButton: WebElement

    fun createDeck(deckName: String) {
        fabButton.click()
        addDeckButton.click()
        deckNameInput.sendKeys(deckName)
        confirmButton.click()
    }

    // Baralho vazio: o app só marca como baralho atual e continua na lista.
    fun selectDeck(deckName: String) {
        deckNameOnList(deckName).click()
    }

    fun openNoteEditor() {
        fabButton.click() // abre o menu
        fabButton.click() // com o menu aberto, vira "adicionar nota"
    }

    fun deckExists(deckName: String): Boolean {
        return try {
            deckNameOnList(deckName).isDisplayed
        } catch (e: Exception) {
            false
        }
    }

    // Número de cartas novas na linha do baralho.
    // A lista recarrega sozinha ao voltar do editor: espera explícita em vez de Thread.sleep.
    fun newCardCount(deckName: String): Int {
        deckNameOnList(deckName)
        val newCount = By.xpath(
            "//*[@resource-id='com.ichi2.anki:id/deck_name' and @text='$deckName']" +
                "/ancestor::*[@resource-id='com.ichi2.anki:id/deck_layout']" +
                "//*[@resource-id='com.ichi2.anki:id/deck_new']"
        )
        var count = 0
        try {
            WebDriverWait(driver, Duration.ofSeconds(5)).until {
                count = driver.findElement(newCount).text.trim().toIntOrNull() ?: 0
                count > 0
            }
        } catch (e: Exception) {
            // continua 0: baralho sem cartas novas
        }
        return count
    }

    // Rola a lista até o baralho aparecer (com muitos baralhos, ele pode estar fora da tela).
    private fun deckNameOnList(deckName: String): WebElement {
        return driver.findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.ichi2.anki:id/decks\"))" +
                    ".scrollIntoView(new UiSelector().resourceId(\"com.ichi2.anki:id/deck_name\").text(\"$deckName\"))"
            )
        )
    }
}
