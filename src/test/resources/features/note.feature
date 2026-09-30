language: pt
Funcionalidade: Gerenciar notas em baralhos

  Cenário: Adicionar uma carta a um baralho
    Dado que existe um baralho "Espanhol"
    Quando eu adiciono uma carta "casa / house"
    Então o baralho tem 1 carta
