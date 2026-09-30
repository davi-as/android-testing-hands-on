# Roteiro Executivo — 90 min (14h00–15h30)

**Apresentador**: Davi Alves de Sousa  
**Público**: ~30 pessoas  
**Local**: Samsung Ocean, Jornada Android

---

## 14h00–14h04 · Payoff (4 min)

Entra, **sem** cumprimenta/slide. Dispara suíte em paralelo:

```bash
./gradlew test
```

Enquanto roda (3 min):

> "Isso aqui levou dois dias pra existir. Vocês vão refazer os pedaços que importam em oitenta minutos."

Leia por cima do output. Aos 3m30s volte ao terminal: ✓ verde.

**Backup**: vídeo `videos/backup-demo.mp4` — roda sem pedir desculpa se falhar.

---

## 14h04–14h14 · Triagem + Anatomia (10 min)

Eles rodando `check.bat` / `check.sh`:

```bash
scripts/check.bat
```

Enquanto eles esperam, disseca teste pronto (DeckPickerPage.kt):

1. **Driver/capabilities** — como Appium acha o aparelho
2. **Locator** — como acha o botão (resource-id > XPath)
3. **Ação** — o clique
4. **Assert** — o que prova que funcionou

Aos 10 min: *"quem teve vermelho?"* → regra de dupla anunciada.

---

## 14h14–14h32 · POM + Primeiro Teste (18 min)

### Dor antes do nome (5 min)

Abre teste feio: locators crus, `findElement` repetido. Mostra o problema refatorando pra Page Object.

> "Ninguém aprende padrão por definição. Aprende por ter visto o problema que o padrão resolve."

### Vez deles (10 min)

Completar `NoteEditorPage.kt`:
- **Piso**: 2 locators + 1 método
- **Teto**: + assert de contagem

Davi circula (20s por pessoa).

### Resposta + Nome (3 min)

Mostra solução, roda verde, nomeia: *"isso é Page Object"*.

---

## 14h32–14h42 · Paralelização (10 min)

### A conta (2 min)

Serial: 40 testes × 25s ≈ 17 min  
2 emuladores: ≈8 min

### Número deles (2 min)

*"Rodem a suíte de vocês agora, anotem o tempo."* (roda enquanto fala resto)

### Como (4 min)

1. Thread count (config)
2. Driver por thread (`ThreadLocal`)
3. Porta + UDID por emulador

### O que quebra (2 min)

Demonstra: dois testes criando baralho com mesmo nome → falha em paralelo.

---

## 14h42–14h56 · BDD (14 min)

### Por quê (3 min)

Mostra teste deles (14h29) → pergunta: "quem consegue ler?"

Mostra mesmo em Gherkin:
```gherkin
Dado que existe um baralho "Espanhol"
Quando eu adiciono uma carta "casa / house"
Então o baralho tem 1 carta
```

### Como conecta (4 min)

```
feature (.feature)
    ↓
step definitions
    ↓
Page Objects
```

**Ponto**: BDD não substitui POM — senta em cima.

### Vez deles (5 min)

Escrevem cenário, ligam 1 step. Resto pronto.

### Preço (2 min)

*"Usamos BDD porque legível por non-dev. Sem non-dev lendo, é custo sem retorno."*

---

## 14h56–15h18 · IA + Economia de Token (22 min)

### Orçamento (2 min)

*"Essa chave tem X tokens. São 30 pessoas. Dá Y por cabeça. Não tem recarga."*

### Contraste (5 min)

Lado a lado:

**Ruim**: prompt genérico → genérico, 6 idas e vindas

**Bom**: prompt com exemplo + locators → colável primeira

Mostra custo somado das iterações.

> "Economia não é prompt curto. É acertar com menos idas e vindas."

### Gerar (8 min)

Page Object novo com IA. **1 prompt por dupla**, escrito antes.

**Regra dura**: força economia de primeira.

### Corrigir (5 min)

Teste quebrado (app mudou). Cole **stack trace cru**, não interpretação.

### 6 Regras (2 min)

Tela pra fotografarem:

1. Aponte pro exemplo
2. Contexto seletivo (1 page + 1 teste + locators)
3. Peça o diff, não o arquivo
4. Uma tarefa por prompt
5. Cole stack trace cru
6. O gasto é a iteração, não o prompt

---

## 15h18–15h28 · Análise de Resultados (10 min)

### Relatório (4 min)

Abre resultado real:
- Teste vermelho com screenshot
- Tempo por teste (topo sempre surpreende)

> "Vermelho sem screenshot é ruído. Com screenshot é informação."

### Flaky (3 min)

> "Teste que passa 8/10 é pior que vermelho. O vermelho conserta. O intermitente você aprende ignorar."

Conta o que time fez (quarentena vs. gambiarra).

### Performance (3 min)

```
40 testes × 3 sleeps × 2s = 4 minutos parados
```

Troca `Thread.sleep` por espera explícita. Mostra tempo novo.

---

## 15h25 (por baixo de performance)

Dispara suíte **com teste deles dentro** pra rodar enquanto fala.

---

## 15h28–15h30 · Fechamento (2 min)

Volta ao terminal: verde.

> "Isso é o que vocês viram quando entraram. Aquilo levou dois dias. Isto aqui tem o teste de vocês."

3 frases finais:
- Repo fica com vocês, respostas em branch
- 6 regras valem pra qualquer coisa
- Próximo passo: pegue teste mais lento do seu projeto, conte quantos `sleep` tem

---

## Plano B — Se atrasar

Ordem de corte (cada = −X min):

1. BDD hands-on → demo (−8)
2. Análise encolhe (−5)
3. POM checkpoint → resposta pronta (−5)

**Nunca cortar IA** — é o diferencial único.

---

## Checklist Final (3–4 dias antes)

- [ ] Repo em C:\Projects\android-testing-hands-on
- [ ] Gradle warm-up rodado (build pronto)
- [ ] APK AnkiDroid downloadado
- [ ] Emulador Pixel 6 API 33 testado (adb devices ok)
- [ ] check.bat testado
- [ ] Appium `driver install uiautomator2` ok
- [ ] Vídeo backup gravado
- [ ] OpenCode chave testada
- [ ] README revisado
- [ ] Instruções enviadas (4 dias antes)

