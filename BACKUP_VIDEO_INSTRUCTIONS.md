# Vídeo de Backup — Demo de Abertura

**Crítico para o evento**: se a live demo falhar, você roda vídeo sem pedir desculpa.

## O que gravar

Execução completa da suíte rodando em paralelo em 2 emuladores:

```bash
./gradlew test
```

Tempo esperado: ~3 min.

**Requisito**: ambos emuladores rodando, verde 100%.

## Como gravar

### Windows

Use **OBS Studio** (free): https://obsproject.com/download

1. Abra OBS
2. **Sources** → clique em `+` → **Screen Capture**
3. Escolha monitor com os 2 emuladores + terminal
4. Clique em **Record** (botão vermelho)
5. Rode: `./gradlew test`
6. Aguarde terminar (3 min)
7. Clique em **Stop Recording**

Vídeo salvo em: `C:\Users\<user>\Videos\obs-studio` (padrão).

### Mac

Use **ScreenFlow** (pago, ~$100) ou **QuickTime** (free):

1. Abra QuickTime Player
2. **Arquivo** → **Gravar Nova Gravação de Tela**
3. Selecione monitor
4. Clique em ⏺️
5. Rode: `./gradlew test`
6. Parar: Ctrl+Cmd+Esc

### Linux

Use **SimpleScreenRecorder**:

```bash
sudo apt install simplescreenrecorder
simplescreenrecorder &
```

1. Output: MP4
2. Selecione região (emuladores + terminal)
3. Clique Record
4. Rode: `./gradlew test`
5. Stop

## Depois de gravar

1. Renomeie: `backup-demo.mp4`
2. Coloque em `videos/` (criar pasta)
3. Verifique: abra vídeo, vê os testes rodando verde?

```
videos/
└── backup-demo.mp4
```

## No dia do evento

Se algo der errado no live:

```bash
# Não tira print, não se desculpa
# Abre o vídeo direto:
vlc videos/backup-demo.mp4
```

Fala por cima do vídeo. Sala não vê diferença.

## Qualidade mínima

- Resolução: 1280x720 (não precisa 4K)
- Codec: H.264
- Áudio: não precisa

---

**Grave 3–4 dias antes do evento. Teste uma vez.**
