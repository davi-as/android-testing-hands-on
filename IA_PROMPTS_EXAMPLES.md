# Exemplos de Prompts IA — As 6 Regras

Use esses exemplos durante o hands-on (bloco de conserto com IA, 14h45–14h55).

---

## Exemplo 1: Prompt BOM (colável de primeira)

```
Siga exatamente o padrão de `DeckPickerPage.kt`. 
Crie `ReviewerPage.kt` com os locators desta tela: 
- com.ichi2.anki:id/show_answer_button
- com.ichi2.anki:id/ease1
- com.ichi2.anki:id/ease3

Métodos:
- showAnswer()
- answerAgain()
- answerGood()

Só o arquivo, sem explicação, sem imports extras.
```

**Resultado esperado**: código colável, primeiro try.

---

## Exemplo 2: Prompt RUIM (caro — precisa de 6 tentativas)

```
Crie um page object para o appium em kotlin para a tela de revisão 
do ankidroid com boas práticas
```

**Resultado esperado**: código genérico, locators inventados, precisa de iterações.

---

## As 6 Regras

1. **Aponte pro exemplo que já existe** — não explique o padrão
   ```
   "Siga o padrão de DeckPickerPage.kt"
   ```

2. **Contexto seletivo**: 1 page object + 1 teste + os locators
   ```
   Nunca: "aqui está todo o repo pra você analisar..."
   Sempre: "use esses 4 resource-ids"
   ```

3. **Peça o diff, não o arquivo**
   ```
   "Altere ReviewerPage.kt: adicione método answerQuestion(rating)"
   ```

4. **Uma tarefa por prompt**
   ```
   Nunca: "crie page object E teste E BDD"
   Sempre: um prompt por coisa
   ```

5. **Cole o stack trace cru, não sua interpretação**
   ```
   Erro do gradle/appium: `java.lang.NullPointerException: locator is null`
   
   Nunca diga: "acho que é um problema de locator"
   Sempre cole: a mensagem exata
   ```

6. **O gasto real é a iteração, não o prompt**
   ```
   Prompt comprido (1000 tokens) acertado na primeira = barato.
   Prompt curto (100 tokens) errado 6x = caro.
   ```

---

## Cenários do hands-on

### Dupla 1: Gerar Page Object novo

**Prompt**: copie o exemplo 1 acima

**Tarefa**: gerar `ReviewerPage.kt` pra tela de revisão

**Limite**: 1 prompt (força economia de primeira)

### Conserto ao vivo: teste antigo quebrado

**Cenário real**: o app mudou e o locator morreu. `LegacyCreateDeckTest` foi escrito quando o botão de criar baralho se chamava `add_deck_action`; no AnkiDroid 2.25 ele é `add_deck_button`.

**Erro** (rode `./gradlew test --tests LegacyCreateDeckTest` e copie do relatório):
```
org.openqa.selenium.NoSuchElementException: An element could not be located on the page
using the given search parameters.
...
at tests.LegacyCreateDeckTest.legacyCreateDeckTest(LegacyCreateDeckTest.kt:...)
```

**Prompt BOM** (contexto seletivo: o erro, o teste e o cheat-sheet):
```
Este teste falha com o erro abaixo. Use os ids de LOCATORS_CHEATSHEET.md.
Me devolva só o diff mínimo de LegacyCreateDeckTest.kt, sem explicação.

<cole o stack trace cru>
```

**Prompt RUIM**:
```
O teste está falhando, acho que é problema de timing, conserta pra mim
```

---

## Orçamento do evento

- **Total**: X tokens
- **Por dupla**: Y tokens
- **Monitorar durante**: 14h45–14h55

Se chegar a 80%, avise a sala: "faltam 2 prompts, escolham com cuidado".

