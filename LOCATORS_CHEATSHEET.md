# AnkiDroid Locators Cheat-sheet

Use `resource-id` quando possível (mais estável que XPath).

> **Status verificado em 2026-09-30** contra o `resources.arsc` do
> `AnkiDroid-2.25.0beta1-arm64-v8a.apk` que está no repo (checagem estática de string —
> confirma que o *nome* do id existe no app, não confirma em qual tela nem a hierarquia).
> ✅ = nome existe no APK · ⚠️ = existe um candidato melhor, não confirmado ao vivo ·
> ❌ = id usado no código não existe neste APK, **precisa trocar antes do evento**.
> Todo ⚠️/❌ deve ser confirmado com o Appium Inspector rodando contra o emulador real
> antes do dia do evento.

## DeckPickerActivity (Tela inicial — lista de baralhos)

| Elemento | Tipo | Locator | resource-id | Status |
|----------|------|---------|-------------|--------|
| Botão flutuante "+" | FAB | id | `com.ichi2.anki:id/fab_main` | ⚠️ era `fab_expand_menu_button`, não existe mais; `fab_main` é o melhor candidato |
| Menu "Adicionar Baralho" | MenuItem | id | `com.ichi2.anki:id/add_deck_button` | ✅ |
| Menu "Adicionar Nota" | MenuItem | id | `com.ichi2.anki:id/menu_add_note` | ✅ |
| Campo de nome do baralho (diálogo criar) | EditText | id | `com.ichi2.anki:id/deck_name_input` | ✅ |
| Confirmar diálogo (OK) | Button | id | `android:id/button1` | ✅ (id padrão de AlertDialog) |
| Baralho na lista | TextView | XPath | `//android.widget.TextView[@text='NOME_DO_BARALHO']` | — (XPath por texto, não por id) |
| Contagem "novo" na linha do baralho | TextView | id | `com.ichi2.anki:id/deck_picker_new` | ⚠️ existe, mas não confirmamos que fica dentro de `deck_picker_group` |
| Campo de busca | EditText | id | `com.ichi2.anki:id/search` | ✅ |

## NoteEditorActivity (Tela de adicionar/editar nota)

| Elemento | Tipo | Locator | resource-id | Status |
|----------|------|---------|-------------|--------|
| Campo "Questão" (frente) | EditText | id genérico + instance | `com.ichi2.anki:id/edit_text`, `instance(0)` | ❌ `note_editor_front` não existe — editor é dinâmico, um `edit_text` por campo do note type |
| Campo "Resposta" (verso) | EditText | id genérico + instance | `com.ichi2.anki:id/edit_text`, `instance(1)` | ❌ `note_editor_back` não existe — mesmo id, `instance(1)` |
| Botão "Salvar" | Button | id | `com.ichi2.anki:id/save` | ✅ |
| Spinner modelo (note type) | Spinner | id | `com.ichi2.anki:id/note_type_spinner` | ⚠️ era `model_spinner`, não existe; `note_type_spinner` é o candidato |

Locator UiAutomator para os campos dinâmicos (usar com `@AndroidFindBy(uiAutomator = ...)`):

```
new UiSelector().resourceId("com.ichi2.anki:id/edit_text").instance(0)   // Frente
new UiSelector().resourceId("com.ichi2.anki:id/edit_text").instance(1)   // Verso
```

## ReviewActivity (Tela de revisão)

| Elemento | Tipo | Locator | resource-id | Status |
|----------|------|---------|-------------|--------|
| Botão "Mostrar Resposta" | Button | id | `com.ichi2.anki:id/show_answer_button` | ✅ |
| Avaliação (fácil/difícil/etc) | Button | xpath | `//android.widget.Button[@text='Fácil']` | — (XPath por texto) |
| Pergunta | TextView | id | `com.ichi2.anki:id/question` | ✅ |
| Resposta | TextView | id | `com.ichi2.anki:id/answer` | ✅ |

## Dicas

- **Sempre use `resource-id` > XPath**: mais rápido, mais estável
- **XPath com `@text`**: cuidado com idioma — app pode estar em PT ou EN
- **Para elementos dinâmicos** (índice na lista): `UiSelector().text("nome")`
- **Se XPath falhar**: abra Appium Inspector, inspecione o elemento, copie exatamente o resource-id

---

## Appium Inspector (obrigatório ANTES do evento, não só demo)

Todo item marcado ⚠️/❌ acima foi encontrado só por inspeção estática do APK (nome existe,
tela e hierarquia não confirmadas). **Antes do evento**, alguém precisa confirmar cada um
ao vivo:

1. Appium rodando: `appium`
2. No IDE, procure por "Appium Inspector" ou abra: http://inspector.appium.io
3. Conecte ao emulador com o `AnkiDroid-2.25.0beta1-arm64-v8a.apk` deste repo instalado
4. Clique no elemento na tela
5. Copie o resource-id exato (não o XPath completo) e atualize esta tabela

## Como eu confirmei os ⚠️/❌ acima (sem emulador)

Sem emulador disponível, dá pra checar se um nome de id *existe em algum lugar do app*
extraindo a tabela de strings do APK (não confirma tela nem hierarquia — só existência):

```bash
unzip -o AnkiDroid-2.25.0beta1-arm64-v8a.apk resources.arsc -d /tmp/apk_inspect
strings -e s /tmp/apk_inspect/resources.arsc | grep -i "nome_do_id"
```

