import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import java.net.URL
import java.time.Duration

object AppiumConfig {

    fun createDriver(udid: String? = null, appiumPort: Int = 4723): AndroidDriver {
        val options = UiAutomator2Options().apply {
            setPlatformName("Android")
            setAutomationName("UiAutomator2")

            // AnkiDroid já instalado no emulador: só abrimos o app
            setAppPackage("com.ichi2.anki")
            setAppActivity("com.ichi2.anki.IntentHandler")

            // Emulador
            if (udid != null) {
                setUdid(udid)
            }

            // Outras capabilities
            noReset()
            // Mantém os dados, mas reinicia o app: todo teste começa na lista de baralhos
            setCapability("forceAppLaunch", true)
            setCapability("shouldTerminateApp", true)
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
