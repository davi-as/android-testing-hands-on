package steps

import AppiumConfig
import io.appium.java_client.android.AndroidDriver
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import org.assertj.core.api.Assertions.assertThat
import pages.DeckPickerPage
import pages.NoteEditorPage

class NoteSteps {

    private lateinit var driver: AndroidDriver
    private lateinit var deckPickerPage: DeckPickerPage
    private lateinit var noteEditorPage: NoteEditorPage

    @Before
    fun setup() {
        driver = AppiumConfig.createDriver()
        deckPickerPage = DeckPickerPage(driver)
        noteEditorPage = NoteEditorPage(driver)
    }

    @After
    fun teardown() {
        driver.quit()
    }

    @Dado("que existe um baralho {string}")
    fun queExisteUmBaralho(nomeBaralho: String) {
        if (!deckPickerPage.deckExists(nomeBaralho)) {
            deckPickerPage.createDeck(nomeBaralho)
        }
        deckPickerPage.tapDeckByName(nomeBaralho)
    }

    // TODO (hands-on de BDD, o step que falta ligar):
    // "carta" chega no formato "frente / verso" (ex.: "casa / house").
    // 1. Separe pelo " / "
    // 2. Chame noteEditorPage.fillQuestion(frente), fillAnswer(verso) e save()
    //    — os mesmos métodos que você implementou no bloco de POM
    @Quando("eu adiciono uma carta {string}")
    fun euAdicionoUmaCarta(carta: String) {
    }

    @Entao("o baralho tem {int} carta")
    fun oBaralhoTemCarta(quantidade: Int) {
        assertThat(noteEditorPage.getCardCount()).isEqualTo(quantidade)
    }
}
