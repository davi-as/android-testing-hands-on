# Setup do Emulador no Android Studio

Guia passo-a-passo para criar e rodar emulador Pixel 6 API 33.

## 1. Abra Android Studio

Clique na aba **Device Manager** (lado direito inferior, ícone de smartphone).

## 2. Criar novo device

Botão **Create Device**.

## 3. Escolher aparelho

Procure por **Pixel 6**.

Se não aparecer, clique em **All** pra listar todos.

Clique em **Pixel 6** → **Next**.

## 4. Escolher imagem do Android

Abra aba **Other Images** (no topo, ao lado de "Recommended").

Procure por **API 33** (pode ser **Android 13**).

Clique em download (⬇️).

Aguarde baixar (~2 GB).

Clique em **Next** quando terminar.

## 5. Verificar settings

Deixe tudo padrão. Clique em **Finish**.

Device deve aparecer na lista.

## 6. Rodar emulador

Na lista de devices, clique em ▶️ (play) ao lado de "Pixel 6 API 33".

Aguarde 2–3 minutos. Tela preta é normal no início.

## 7. Confirmar que tá online

Abra terminal:

```bash
adb devices
```

Saída deve ser:

```
List of attached devices
emulator-5554    device
```

Se disser "offline", aguarde mais 1 min e rode de novo.

## 8. Testar

No emulador, clique em alguns botões, veja se responde.

Pronto! Deixe rodando até antes do evento.

---

## Se der erro

### "Virtual device failed to initialize"

Geralmente é espaço em disco ou virtualização desabilitada.

**Windows**: vai ao BIOS, ativa "Virtualization" ou "Hyper-V".

**Mac**: já vem com virtualização.

**Linux**: ativa KVM: `sudo kvm-ok`.

### "emulator: ERROR: x86 emulation currently requires hardware acceleration"

Virtualização desabilitada no BIOS.

Reinicie → BIOS (F2, Del, etc) → ativa VT-x ou AMD-V → salva → reinicia.

### Emulador não abre

Tenta fechar e reabrir (⏹️ depois ▶️).

Se persistir, deleta o device (⋮ → Delete) e recriar.

---

**Tire um print quando estiver rodando e mande antes do evento.**
