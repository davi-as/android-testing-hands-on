# Exemplos de Prompts IA — As 6 Regras

Use esses exemplos durante o hands-on (14h56–15h18).

---

## Exemplo 1: Prompt BOM (colável de primeira)

```
Siga exatamente o padrão de `DeckPickerPage.kt`. 
Crie `ReviewerPage.kt` com os locators desta tela: 
- com.ichi2.anki:id/show_answer_button
- com.ichi2.anki:id/question
- com.ichi2.anki:id/answer

Métodos:
- showAnswer(): void
- getQuestionText(): String
- getAnswerText(): String

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

### Dupla 2+: Corrigir Page Object quebrado

**Cenário real**: app mudou, locator morreu

**Erro**:
```
io.appium.java_client.remote.MobileCommand: org.openqa.selenium.NoSuchElementException:
An element could not be located on the page using the given search parameters 
("xpath", "//android.widget.Button[@resource-id='ANTIGO']").
```

**Prompt BOM**:
```
Cole o stack trace acima. ReviewerPage.kt falha na linha X. 
Veja LOCATORS_CHEATSHEET.md, o novo resource-id é Y.
Corrija o locator.
```

**Prompt RUIM**:
```
O teste está falhando, conserta pra mim
```

---

## Orçamento do evento

- **Total**: X tokens
- **Por dupla**: Y tokens
- **Monitorar durante**: 14h56–15h18

Se chegar a 80%, avise a sala: "faltam 2 prompts, escolham com cuidado".

