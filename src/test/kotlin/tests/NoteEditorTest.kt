package tests

import AppiumConfig
import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pages.NoteEditorPage

class NoteEditorTest {

    private lateinit var driver: AndroidDriver
    private lateinit var noteEditorPage: NoteEditorPage

    @BeforeEach
    fun setup() {
        driver = AppiumConfig.createDriver()
        noteEditorPage = NoteEditorPage(driver)
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun `addCardToDeckTest`() {
        // Preenchimento de nota
        noteEditorPage.fillQuestion("casa")
        noteEditorPage.fillAnswer("house")
        noteEditorPage.save()

        // Verificação: nota foi salva
        assert(true) // Placeholder até implementar
    }
}
