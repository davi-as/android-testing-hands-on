package tests

import AppiumConfig
import TestData
import io.appium.java_client.android.AndroidDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

// Teste antigo, escrito antes de o projeto ter Page Objects.
// Locators crus, direto no teste.
class LegacyCreateDeckTest {

    private lateinit var driver: AndroidDriver

    @BeforeEach
    fun setup() {
        driver = AppiumConfig.createDriver()
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun legacyCreateDeckTest() {
        val deck = TestData.uniqueName("Italiano")

        driver.findElement(By.id("com.ichi2.anki:id/fab_main")).click()
        driver.findElement(By.id("com.ichi2.anki:id/add_deck_action")).click()
        driver.findElement(By.id("com.ichi2.anki:id/dialog_text_input")).sendKeys(deck)
        driver.findElement(By.id("android:id/button1")).click()

        val decks = driver.findElements(
            By.xpath("//*[@resource-id='com.ichi2.anki:id/deck_name' and @text='$deck']")
        )
        assertThat(decks).isNotEmpty()
    }
}
