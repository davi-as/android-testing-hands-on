package pages

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.pagefactory.AppiumFieldDecorator
import org.openqa.selenium.support.PageFactory
import java.time.Duration

abstract class BasePage(protected val driver: AndroidDriver) {
    init {
        // Sem o Duration, o decorator espera só 1s e sobrescreve o implicitlyWait do AppiumConfig
        PageFactory.initElements(AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this)
    }
}
