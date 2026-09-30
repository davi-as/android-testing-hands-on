package tests

import AppiumConfig
import TestData
import io.appium.java_client.android.AndroidDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.DeckPickerPage
import pages.NoteEditorPage

class NoteEditorTest {

    private lateinit var driver: AndroidDriver
    private lateinit var deckPicker: DeckPickerPage
    private lateinit var noteEditor: NoteEditorPage

    @BeforeEach
    fun setup() {
        driver = AppiumConfig.createDriver()
        deckPicker = DeckPickerPage(driver)
        noteEditor = NoteEditorPage(driver)
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun addCardToDeckTest() {
        val deck = TestData.uniqueName("Espanhol")
        deckPicker.createDeck(deck)
        deckPicker.selectDeck(deck)

        deckPicker.openNoteEditor()
        noteEditor.fillCard("casa", "house")
        noteEditor.save()
        noteEditor.backToDeckList()

        assertThat(deckPicker.newCardCount(deck)).isEqualTo(1)
    }
}
