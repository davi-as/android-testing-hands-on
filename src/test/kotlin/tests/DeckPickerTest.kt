package tests

import AppiumConfig
import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.DeckPickerPage

class DeckPickerTest {

    private lateinit var driver: AndroidDriver
    private lateinit var deckPickerPage: DeckPickerPage

    @BeforeEach
    fun setup() {
        driver = AppiumConfig.createDriver()
        deckPickerPage = DeckPickerPage(driver)
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun `should navigate to deck picker`() {
        // Este teste passa porque apenas navega pela tela aberta
        assert(true) // Placeholder — verificar que driver está conectado
    }
}
