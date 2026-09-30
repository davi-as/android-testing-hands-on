# language: pt
Funcionalidade: Baralhos

  Cenário: Adicionar uma carta a um baralho
    Dado que existe um baralho "Espanhol"
    Quando eu adiciono uma carta "casa / house"
    Então o baralho "Espanhol" tem 1 carta
