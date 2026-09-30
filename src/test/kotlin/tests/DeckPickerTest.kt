package tests

import AppiumConfig
import TestData
import io.appium.java_client.android.AndroidDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.DeckPickerPage

class DeckPickerTest {

    private lateinit var driver: AndroidDriver
    private lateinit var deckPicker: DeckPickerPage

    @BeforeEach
    fun setup() {
        driver = AppiumConfig.createDriver()
        deckPicker = DeckPickerPage(driver)
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun createDeckTest() {
        val deck = TestData.uniqueName("Espanhol")

        deckPicker.createDeck(deck)

        assertThat(deckPicker.deckExists(deck)).isTrue()
    }

    @Test
    fun newDeckStartsEmptyTest() {
        val deck = TestData.uniqueName("Inglês")

        deckPicker.createDeck(deck)

        assertThat(deckPicker.newCardCount(deck)).isEqualTo(0)
    }
}
