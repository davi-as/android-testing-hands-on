# Rodando os testes

## Pré-requisitos

- Emulador Pixel 6 API 33 rodando (`adb devices` mostra "device")
- Appium rodando em outro terminal: `appium`
- Dependências baixadas e código compilando: `./gradlew compileTestKotlin`

## Rodar todos os testes

```bash
./gradlew test
```

## Rodar teste específico

```bash
./gradlew test --tests DeckPickerTest
```

```bash
./gradlew test --tests NoteEditorTest
./gradlew test --tests LegacyCreateDeckTest
./gradlew test --tests RunCucumberTest     # cenários BDD (.feature)
```

## Paralelização (2 emuladores): fora do hands-on

No evento isso só é mencionado: dois emuladores no mesmo notebook travam a máquina. Fica como próximo passo.

Edite `build.gradle.kts`, altere:

```kotlin
systemProperties["junit.jupiter.execution.parallel.enabled"] = "true"
```

Depois:

```bash
./gradlew test
```

**Aviso**: precisa de 2 emuladores rodando. Configure em `AppiumConfig.kt`:

```kotlin
fun createDriver(udid: String? = null, appiumPort: Int = 4723): AndroidDriver {
    // Primera instancia: udid = "emulator-5554", port = 4723
    // Segunda instancia: udid = "emulator-5556", port = 4724
}
```

## Ver resultados

HTML report em:

```
build/reports/tests/test/index.html
```

Abra no navegador.

## Limpar antes de rodar novamente

```bash
./gradlew clean
./gradlew test
```

## Troubleshooting

### "Connection refused on 127.0.0.1:4723"

Appium não tá rodando. Em outro terminal:

```bash
appium
```

### "Failed to run test: Device is offline"

Emulador não responde. Reinicia:

```bash
adb shell "reboot -p"
```

Aguarde 30s e rode de novo.

### "No matching constructor found"

Gradle versionamento fora. Tenta:

```bash
./gradlew clean --refresh-dependencies
./gradlew test
```

