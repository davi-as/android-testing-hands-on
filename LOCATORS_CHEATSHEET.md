# AnkiDroid Locators Cheat-sheet

Use `resource-id` sempre que der (mais estável que XPath).

> Ids conferidos no código-fonte do **AnkiDroid 2.25.0** (layouts em `AnkiDroid/src/main/res`
> da tag `v2.25.0`). Isso garante que o id existe e em qual tela ele fica; a confirmação final
> é rodar os testes no emulador.

## DeckPicker (tela inicial: lista de baralhos)

| Elemento | resource-id | Observação |
|----------|-------------|------------|
| Botão flutuante "+" | `com.ichi2.anki:id/fab_main` | 1º toque abre o menu. Com o menu aberto, tocar de novo abre o editor de nota. |
| Menu "Criar baralho" | `com.ichi2.anki:id/add_deck_button` | Só aparece com o menu do "+" aberto. Até a versão 2.17 se chamava `add_deck_action`. |
| Campo do nome (diálogo "Criar baralho") | `com.ichi2.anki:id/dialog_text_input` | |
| Botão OK de diálogo | `android:id/button1` | Id padrão do Android |
| Lista de baralhos | `com.ichi2.anki:id/decks` | RecyclerView, rola |
| Linha de um baralho | `com.ichi2.anki:id/deck_layout` | |
| Nome do baralho na linha | `com.ichi2.anki:id/deck_name` | Filtre pelo texto |
| Cartas novas na linha | `com.ichi2.anki:id/deck_new` | Dentro da mesma `deck_layout` do nome |

Achar um baralho, rolando a lista se precisar:

```
new UiScrollable(new UiSelector().resourceId("com.ichi2.anki:id/decks"))
    .scrollIntoView(new UiSelector().resourceId("com.ichi2.anki:id/deck_name").text("Espanhol"))
```

## NoteEditor (adicionar carta)

| Elemento | resource-id | Observação |
|----------|-------------|------------|
| Campos da nota (Frente, Verso...) | `com.ichi2.anki:id/edit_text` | Um por campo, **todos com o mesmo id**. Frente = `instance(0)`, Verso = `instance(1)` no tipo de nota "Básico". |
| Nome do campo | `com.ichi2.anki:id/label` | |
| Botão Salvar (✓ na barra) | `com.ichi2.anki:id/action_save` | Depois de salvar, o editor continua aberto. |
| Baralho de destino | `com.ichi2.anki:id/note_deck_name` | |
| Tipo de nota | `com.ichi2.anki:id/note_type_spinner` | |

Locator UiAutomator para os campos (usar com `@AndroidFindBy(uiAutomator = ...)`):

```
new UiSelector().resourceId("com.ichi2.anki:id/edit_text").instance(0)   // Frente
new UiSelector().resourceId("com.ichi2.anki:id/edit_text").instance(1)   // Verso
```

### Pronto pra colar: solução dos TODOs do `NoteEditorPage.kt`

**TODO 1 — campo Frente:**

```kotlin
@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(0)")
private lateinit var frontField: WebElement
```

**TODO 2 — campo Verso:**

```kotlin
@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ichi2.anki:id/edit_text\").instance(1)")
private lateinit var backField: WebElement
```

**TODO 3 — preencher a carta:**

```kotlin
fun fillCard(front: String, back: String) {
    frontField.sendKeys(front)
    backField.sendKeys(back)
}
```

> A barra antes das aspas internas (`\"`) é obrigatória: sem ela o Kotlin acha que o texto acabou.

Rodar:

```bash
./gradlew test --tests NoteEditorTest
```

### Pronto pra colar: solução do TODO do Cucumber (`NoteSteps.kt`)

Depende dos TODOs do `NoteEditorPage.kt` acima: faça aqueles primeiro.

```kotlin
@Quando("eu adiciono uma carta {string}")
fun euAdicionoUmaCarta(carta: String) {
    val (frente, verso) = carta.split(" / ")
    deckPicker.openNoteEditor()
    noteEditor.fillCard(frente, verso)
    noteEditor.save()
    noteEditor.backToDeckList()
}
```

Rodar:

```bash
./gradlew test --tests RunCucumberTest
```

## Reviewer (revisão)

| Elemento | resource-id |
|----------|-------------|
| Botão "Mostrar resposta" | `com.ichi2.anki:id/show_answer_button` |
| Área do cartão | `com.ichi2.anki:id/flashcard` |
| Botões de avaliação (De novo, Difícil, Bom, Fácil) | `com.ichi2.anki:id/ease1` a `ease4` |

## Dicas

- **`resource-id` > XPath**: um é nome, o outro é endereço.
- **XPath com `@text`**: cuidado com o idioma, o app pode estar em PT ou EN.
- **Na dúvida**: abra o Appium Inspector (http://inspector.appium.io), clique no elemento e copie o resource-id.
