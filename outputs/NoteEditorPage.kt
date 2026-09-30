package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class NoteEditorPage(driver: AndroidDriver) : BasePage(driver) {

    // "note_editor_front"/"note_editor_back" não existem neste APK — editor dinâmico,
    // um EditText por campo do note type, mesmo id genérico "edit_text". Frente = instance(0),
    // Verso = instance(1). Confirmado que "edit_text" existe no resources.arsc; a ordem
    // (0=Frente, 1=Verso) assume o note type "Basic" e precisa validação ao vivo.
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(0)")
    private lateinit var questionField: WebElement

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(1)")
    private lateinit var answerField: WebElement

    @AndroidFindBy(id = "com.ichi2.anki:id/save")
    private lateinit var saveButton: WebElement

    fun fillQuestion(text: String) {
        questionField.clear()
        questionField.sendKeys(text)
    }

    fun fillAnswer(text: String) {
        answerField.clear()
        answerField.sendKeys(text)
    }

    fun save() {
        saveButton.click()
    }

    // Extra: contar cartas no baralho
    // "card_count" não existe neste APK. Melhor candidato: o número de cartas "novas" mostrado
    // na linha do baralho na DeckPicker (deck_picker_new), já que uma carta recém-criada ainda
    // não foi estudada. Assume que é o PRIMEIRO baralho da lista — se houver mais de um baralho
    // na tela, escopar por nome (não feito aqui). Não confirmado ao vivo com Appium Inspector.
    fun getCardCount(): Int {
        return try {
            val newCountText = driver.findElement(
                By.id("com.ichi2.anki:id/deck_picker_new")
            ).text
            newCountText.toIntOrNull() ?: 0
        } catch (e: Exception) {
            0
        }
    }
}
