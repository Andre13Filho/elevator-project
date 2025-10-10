# Sistema de Controle de Elevador

> [cite_start]Atividade em Grupo para a disciplina de Programação Orientada a Objetos (POO) da Uniube. [cite: 1, 2] > [cite_start]**Valor:** 2,00 pontos [cite: 2, 69] > [cite_start]**Data de Entrega:** 22/10/2025 [cite: 2, 69]

---

## 📝 Descrição Geral

[cite_start]Este projeto consiste na implementação de um sistema de controle de elevadores em Java, utilizando os conceitos de Programação Orientada a Objetos. [cite: 5] [cite_start]O objetivo é simular o comportamento de um ou mais elevadores dentro de um prédio, gerenciando regras como limite de pessoas, movimentação entre andares e modo de manutenção. [cite: 6]

---

## 🏛️ Estrutura do Sistema

[cite_start]O sistema é modelado em quatro classes principais, cada uma com uma função específica: [cite: 7]

- [cite_start]**`Elevador`**: Representa uma única unidade de elevador, mantendo seus atributos e ações básicas. [cite: 8, 9]
- [cite_start]**`Predio`**: Representa o prédio e gerencia a lista de elevadores existentes. [cite: 10]
- [cite_start]**`ElevadorService`**: Contém as regras de negócio e a lógica de operação do elevador (subir, descer, embarcar pessoas). [cite: 11]
- [cite_start]**`Main`**: Responsável pela execução do programa, simulação e exibição de resultados para o usuário. [cite: 12]

[cite_start]**Importante:** Nenhuma classe de negócio (`Elevador`, `Predio`, `ElevadorService`) deve conter comandos de exibição como `System.out.println`. [cite: 13] [cite_start]Toda a interação com o usuário deve ser centralizada na classe `Main`. [cite: 15]

---

## 📋 Requisitos do Sistema

### 1. Classe `Elevador`

#### Atributos

- [cite_start]`int andarAtual` (inicia em 0, que é o térreo) [cite: 19]
- [cite_start]`int totalAndares` [cite: 20]
- [cite_start]`int capacidade` (número máximo de pessoas) [cite: 21]
- [cite_start]`int pessoasPresentes` [cite: 22]
- [cite_start]`boolean emManutencao` [cite: 32]

#### Métodos Obrigatórios

- [cite_start]`void inicializar(int capacidade, int totalAndares)` [cite: 35]
- [cite_start]`void entrar()`: Adiciona uma pessoa, se houver espaço e o elevador não estiver em manutenção. [cite: 36]
- [cite_start]`void sair()`: Remove uma pessoa, se houver alguém dentro e o elevador não estiver em manutenção. [cite: 37]
- [cite_start]`void subir()`: Sobe um andar, se não estiver no último andar. [cite: 38]
- [cite_start]`void descer()`: Desce um andar, se não estiver no térreo. [cite: 39]
- [cite_start]`void colocarEmManutencao()` e `void liberarManutencao()` [cite: 40]
- [cite_start]Métodos de acesso (getters) para todos os atributos. [cite: 41]

### 2. Classe `Predio`

[cite_start]Representa o prédio que contém um ou mais elevadores. [cite: 43]

#### Atributos

- [cite_start]`String nome` [cite: 45]
- [cite_start]`List<Elevador> elevadores` [cite: 46]

#### Métodos Obrigatórios

- [cite_start]`void adicionarElevador(Elevador elevador)` [cite: 48]
- [cite_start]`Elevador getElevador(int indice)` [cite: 49]
- [cite_start]`int getQuantidadeElevadores()` [cite: 50]
- [cite_start]`String getNome()` [cite: 51]

### 3. Classe `ElevadorService`

[cite_start]Responsável por conter as regras de negócio do sistema. [cite: 54]

#### Métodos Obrigatórios

- [cite_start]`void moverElevador(Elevador elevador, int destino)`: Move o elevador andar por andar até o destino. [cite: 56, 57]
- [cite_start]`void embarcarPessoas(Elevador elevador, int quantidade)`: Adiciona um grupo de pessoas, respeitando a capacidade máxima. [cite: 58]
- [cite_start]`void desembarcarPessoas(Elevador elevador, int quantidade)`: Remove um grupo de pessoas. [cite: 59]
- [cite_start]`String gerarStatus(Elevador elevador)`: Retorna uma `String` formatada com o estado atual do elevador. [cite: 60]

### 4. Classe `Main`

[cite_start]Deve conter o método `main` para realizar a simulação. [cite: 62]

#### Requisitos da Simulação:

- [cite_start]Crie um objeto `Predio` e adicione dois elevadores com capacidades e andares distintos. [cite: 63]
- Simule as seguintes operações:
  - [cite_start]Entrada e saída de pessoas. [cite: 65]
  - [cite_start]Movimentação entre andares (subida e descida). [cite: 66]
  - [cite_start]Ativação e desativação do modo de manutenção. [cite: 67]
- [cite_start]Exiba o status atual dos elevadores após cada operação realizada. [cite: 68]

---
