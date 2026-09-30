# Android Testing – Hands-on (Samsung Ocean Jornada)

Repositório do hands-on de automação de testes Android com Appium, Cucumber e Kotlin.

**Evento**: Jornada Android – 21 de setembro de 2026 (Samsung Ocean)  
**Duração**: 90 min  
**Apresentador**: Davi Alves de Sousa

---

## Pré-setup (faça em casa, 3–4 dias antes)

Siga **todos** os passos abaixo. Não pule nenhum.

### 1. JDK 17

Baixe e instale a JDK 17 (não é a 21, é a 17).

**Windows/Mac/Linux**:
```bash
java -version
```

Deve mostrar `openjdk version "17.X.X"` ou similar. Se não tiver, instale:
- Windows: https://www.oracle.com/java/technologies/downloads/#java17
- Mac: `brew install openjdk@17`
- Linux (Ubuntu): `sudo apt install openjdk-17-jdk`

### 2. Android Studio

Baixe e instale: https://developer.android.com/studio

Inclui Android SDK, SDK Manager, AVD Manager e `adb`.

### 3. Emulador Pixel 6, API 33

**Crítico**: este passo falha mais vezes. Se der erro, tire uma print e mande antes do evento.

Abra Android Studio → **Device Manager** (lado direito) → **Create Device**.

1. Escolha **Pixel 6** (aparelho)
2. Clique em **Other Images** → **API 33** → download
3. Clique em ✓ pra confirmar
4. Boot do emulador: clique na seta ▶ ao lado do Pixel 6
5. Espere carregar (2–3 min). Tela deve mostrar Android normal.
6. Abra terminal:

```bash
adb devices
```

Deve listar:
```
List of attached devices
emulator-5554    device
```

Se não aparecer, o emulador não tá online — aguarde ou reinicie.

**Mantenha o emulador rodando** até antes do evento.

### 4. Git + clone do repo

```bash
cd C:\Projects
git clone https://github.com/davi-as/android-testing-hands-on.git
cd android-testing-hands-on
```

### 5. Node.js 20+ e Appium

Baixe: https://nodejs.org/ (LTS)

Depois:

```bash
npm install -g appium
appium driver install uiautomator2
```

O `driver install` baixa ~100 MB — **tem que ser feito em casa**. No evento vai dar timeout.

Confirme:

```bash
appium -v
appium driver list
```

Deve listar `uiautomator2` com status "installed".

### 6. AnkiDroid APK

Baixe a última release: https://github.com/ankidroid/Anki-Android/releases

Procure por `AnkiDroid-X.XX.apk` (não a versão de debug).

Coloque na raiz do repo:

```bash
C:\Projects\android-testing-hands-on\AnkiDroid.apk
```

### 7. Warm-up Gradle

```bash
cd C:\Projects\android-testing-hands-on
./gradlew build
```

Primeira execução baixa dependências (~300 MB). Tem que ser em casa.

### 8. OpenCode (opcional para o dia)

Instale: https://opencode.ai/docs

```bash
npm install -g opencode
```

### 9. Validação final

```bash
cd C:\Projects\android-testing-hands-on

# Windows
scripts\check.bat

# Mac/Linux
bash scripts/check.sh
```

Todos os itens devem estar ✓. Se algum estiver ✘, conserte **antes do evento**.

---

## Setup no dia (14h00)

1. Emulador já deve estar ligado
2. Rode `check.bat` (ou `check.sh`)
3. Pronto

---

## Estrutura do projeto

```
android-testing-hands-on/
├── build.gradle.kts          # Configuração Gradle + dependências
├── settings.gradle.kts
├── AnkiDroid.apk             # Aplicação alvo
├── src/
│   └── test/
│       └── kotlin/
│           ├── AppiumConfig.kt         # Driver factory
│           ├── pages/
│           │   ├── BasePage.kt         # Classe base
│           │   ├── DeckPickerPage.kt   # Exemplo pronto
│           │   └── NoteEditorPage.kt   # Exemplo vazio (hands-on)
│           └── tests/
│               ├── DeckPickerTest.kt   # Teste que passa
│               └── NoteEditorTest.kt   # Teste que falha (hands-on)
├── scripts/
│   ├── check.sh              # Validador (Unix)
│   └── check.bat             # Validador (Windows)
└── README.md                 # Este arquivo
```

---

## Problemas comuns

### "emulator-5554: offline"

Emulador não tá respondendo.

**Solução**:
1. Feche o emulador (Android Studio → Device Manager → ⏹️)
2. Aguarde 10s
3. Reinicie (▶️)
4. Aguarde boot completo (2–3 min)

### "JDK 17 not found"

`java -version` mostra 11, 8 ou erro.

**Windows**:
```bash
where java
```

Se apontar pra outra versão, desinstale e instale JDK 17 de novo. Ou:

```bash
set JAVA_HOME=C:\Program Files\Java\jdk-17
```

**Mac/Linux**:

```bash
/usr/libexec/java_home -v 17
```

Se vazio, instale:
```bash
brew install openjdk@17
brew link openjdk@17
```

### "Appium connection refused"

Appium não tá rodando.

Abra outro terminal:

```bash
appium
```

Deixe rodando enquanto testa.

### "Port 4723 already in use"

Appium já tá rodando.

```bash
# Unix
lsof -i :4723

# Windows
netstat -ano | findstr :4723
```

Mate o processo ou use porta diferente em `AppiumConfig.kt`.

### "Virtualization disabled in BIOS"

Emulador não abre ou é muito lento.

**Windows**: 
- Reinicie em BIOS (F2, Del ou F12 during boot — depende da máquina)
- Procure por "Virtualization", "VT-x", "AMD-V" ou "Hyper-V"
- Ative (enable)
- Salve e reinicie

Se não souber fazer, peça ajuda de suporte técnico.

### "Gradle build fails"

Erro ao rodar `./gradlew build`.

**Solução**:
```bash
./gradlew clean
./gradlew build
```

Se persistir, delete cache:
```bash
rm -rf ~/.gradle/caches
./gradlew build
```

### "Não consigo rodar os testes"

Emulador ligado? `adb devices` mostra "device"?

```bash
adb devices
```

Se vazio, leia "emulator-5554: offline" acima.

Se aparecer, tente:

```bash
./gradlew test
```

Se falhar, copie a **mensagem de erro completa** e mande pra Davi **com antecedência**.

---

## Cheat-sheet de locators (AnkiDroid)

Será fornecido no evento como PNG com resource-ids anotados.

---

## Respostas dos hands-on

Pasta `outputs/` contém as soluções esperadas. **Não consulte durante o evento**, só depois.

---

## Suporte pré-evento

Slack/Teams: será disponibilizado 3–4 dias antes.

Qualquer dúvida no setup, mande print + erro completo.

---

**Boa sorte!**
