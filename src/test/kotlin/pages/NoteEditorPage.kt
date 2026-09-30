package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

// Tela de adicionar carta.
class NoteEditorPage(driver: AndroidDriver) : BasePage(driver) {

    // O editor cria um campo por item do tipo de nota, todos com o MESMO resource-id.
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(0)")
    private lateinit var frontField: WebElement

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(1)")
    private lateinit var backField: WebElement

    @AndroidFindBy(id = "com.ichi2.anki:id/action_save")
    private lateinit var saveButton: WebElement

    fun fillCard(front: String, back: String) {
        frontField.sendKeys(front)
        backField.sendKeys(back)
    }

    fun save() {
        saveButton.click()
    }

    // Depois de salvar, o editor continua aberto (pronto para a próxima carta).
    fun backToDeckList() {
        if (driver.isKeyboardShown) {
            driver.hideKeyboard()
        }
        driver.navigate().back()
    }
}
