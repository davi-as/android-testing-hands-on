import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URL
import java.time.Duration

object AppiumConfig {

    fun createDriver(udid: String? = null, appiumPort: Int = 4723): AndroidDriver {
        val options = UiAutomator2Options().apply {
            platformName = "Android"
            automationName = "UiAutomator2"

            // APK da aplicação
            app = System.getenv("ANKIDROID_APK")
                ?: "/path/to/AnkiDroid.apk"

            // Emulador
            if (udid != null) {
                udid(udid)
            }

            // Outras capabilities
            noReset()
            autoGrantPermissions()
            setCapability("disableWindowAnimation", true)
            setCapability("disableSystemAnimations", true)
        }

        val appiumUrl = URL("http://127.0.0.1:$appiumPort")
        val driver = AndroidDriver(appiumUrl, options)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))

        return driver
    }
}
