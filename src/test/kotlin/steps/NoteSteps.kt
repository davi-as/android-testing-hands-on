package steps

import AppiumConfig
import TestData
import io.appium.java_client.android.AndroidDriver
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import org.assertj.core.api.Assertions.assertThat
import pages.DeckPickerPage
import pages.NoteEditorPage

// O Cucumber cria uma instância desta classe por cenário: o estado abaixo não vaza entre cenários.
class NoteSteps {

    private lateinit var driver: AndroidDriver
    private lateinit var deckPicker: DeckPickerPage
    private lateinit var noteEditor: NoteEditorPage

    // Nome do cenário ("Espanhol") -> nome real no app ("Espanhol 48213")
    private val decks = mutableMapOf<String, String>()

    @Before
    fun setup() {
        driver = AppiumConfig.createDriver()
        deckPicker = DeckPickerPage(driver)
        noteEditor = NoteEditorPage(driver)
    }

    @After
    fun teardown() {
        driver.quit()
    }

    @Dado("que existe um baralho {string}")
    fun queExisteUmBaralho(nome: String) {
        val deck = TestData.uniqueName(nome)
        decks[nome] = deck
        deckPicker.createDeck(deck)
        deckPicker.selectDeck(deck)
    }

    // TODO: o step que falta ligar.
    // "carta" chega no formato "frente / verso" (ex.: "casa / house").
    // 1. Separe pelo " / "
    // 2. Faça o mesmo que o addCardToDeckTest: abrir o editor, preencher, salvar e voltar
    @Quando("eu adiciono uma carta {string}")
    fun euAdicionoUmaCarta(carta: String) {
    }

    @Entao("o baralho {string} tem {int} carta")
    fun oBaralhoTemCarta(nome: String, quantidade: Int) {
        assertThat(deckPicker.newCardCount(decks.getValue(nome))).isEqualTo(quantidade)
    }
}
