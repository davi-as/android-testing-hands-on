package pages

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class DeckPickerPage(driver: AndroidDriver) : BasePage(driver) {

    // "fab_expand_menu_button" não existe mais no APK do repo (confirmado no resources.arsc).
    // "fab_main" é o candidato mais plausível (aparece junto de fabBGLayout/fabLinearLayout,
    // família de ids de FAB), mas não foi confirmado ao vivo com o Appium Inspector.
    @FindBy(id = "com.ichi2.anki:id/fab_main")
    private lateinit var fabMenuButton: WebElement

    @FindBy(id = "com.ichi2.anki:id/menu_add_note")
    private lateinit var addNoteButton: WebElement

    fun tapAddDeckButton() {
        driver.findElement(By.id("com.ichi2.anki:id/add_deck_button")).click()
    }

    fun createDeck(deckName: String) {
        tapAddDeckButton()
        driver.findElement(By.id("com.ichi2.anki:id/deck_name_input")).sendKeys(deckName)
        driver.findElement(By.id("android:id/button1")).click()
    }

    fun tapDeckByName(deckName: String) {
        driver.findElement(By.xpath("//android.widget.TextView[@text='$deckName']")).click()
    }

    fun deckExists(deckName: String): Boolean {
        return try {
            driver.findElement(By.xpath("//android.widget.TextView[@text='$deckName']"))
            true
        } catch (e: Exception) {
            false
        }
    }
}
