package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AndroidFindBy
import org.openqa.selenium.WebElement

class NoteEditorPage(driver: AndroidDriver) : BasePage(driver) {

    // TODO: locator para campo de questão (frente)
    // "note_editor_front" NÃO existe neste APK (confirmado no resources.arsc) — o editor é
    // dinâmico, um EditText por campo do note type, todos com o MESMO id genérico
    // "com.ichi2.anki:id/edit_text". Use UiSelector + instance(0) para o 1º campo (Frente):
    // new UiSelector().resourceId("com.ichi2.anki:id/edit_text").instance(0)
    @AndroidFindBy(uiAutomator = "")
    private lateinit var questionField: WebElement

    // TODO: locator para campo de resposta (verso) — mesmo id genérico, instance(1)
    @AndroidFindBy(uiAutomator = "")
    private lateinit var answerField: WebElement

    // TODO: implementar método para preencher questão
    fun fillQuestion(text: String) {
        // Complete this
    }

    // TODO: implementar método para preencher resposta
    fun fillAnswer(text: String) {
        // Complete this
    }

    // TODO: implementar método para salvar nota
    fun save() {
        // Complete this
    }

    // TODO (bônus/teto + motor do step de BDD): contar cartas do baralho após salvar
    // "card_count" NÃO existe neste APK. Candidato mais plausível: o número que aparece
    // na linha do baralho na tela DeckPicker (com.ichi2.anki:id/deck_picker_new — carta nova
    // ainda não estudada). Precisa confirmar ao vivo com o Appium Inspector antes do evento.
    fun getCardCount(): Int {
        return 0
    }
}
