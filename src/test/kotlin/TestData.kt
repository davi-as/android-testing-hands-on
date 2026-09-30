object TestData {

    // Os testes rodam com noReset: os baralhos de execuções anteriores continuam no app.
    // Um sufixo por execução evita "baralho já existe" e contagem de cartas acumulada.
    fun uniqueName(base: String): String = "$base ${System.currentTimeMillis() % 100000}"
}
