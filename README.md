# Sistema de Controle de Elevador

Projeto acadêmico (POO) que simula o comportamento de um ou mais elevadores em um prédio.

## ✨ Objetivo

Criar uma aplicação em Java que modele elevadores, suas regras de operação (capacidade, movimentação, manutenção) e que permita executar uma simulação através de um `main` que exiba o estado dos elevadores após cada operação.

## 🗂 Estrutura do repositório

Arquivos principais:

- `app/main.java` — Classe com o método `main` que executa a simulação.
- `model/elevator.java` — Modelo da entidade Elevador (atributos e getters/setters).
- `model/build.java` — (opcional) Arquivo auxiliar (atualmente vazio).
- `service/elevatorService.java` — Regras de negócio e operações sobre o elevador.

> Observação: neste repositório alguns arquivos de modelo estão vazios (ou incompletos). Veja a seção "Próximos passos" abaixo para sugestões de implementação.

## 📌 Responsabilidades das classes

- `Elevador` (model): mantém estado (andar atual, total de andares, capacidade, pessoas presentes, emManutencao) e expõe getters/setters.
- `Predio` (não existe um arquivo explícito atualmente): é responsável por agregar elevadores e oferecer métodos para adicionar/recuperar elevadores.
- `ElevadorService` (service): implementa lógica como subir/descer, embarcar/desembarcar pessoas e gerar uma string de status.
- `Main` (`app/main.java`): interação com o usuário / execução da simulação e exibição dos estados com `System.out.println`.

## Regras e contrato por classe

A seguir estão regras claras (contratos) para cada classe do sistema. Use estas regras como referência de implementação e para testes.

### Elevador (classe `Elevador`)

- Atributos obrigatórios:

  - `private int andarAtual` — valor inicial: 0 (térreo).
  - `private int totalAndares` — número máximo de andares (>= 0).
  - `private int capacidade` — capacidade máxima de pessoas (>= 1).
  - `private int pessoasPresentes` — número atual de pessoas (>= 0 e <= capacidade).
  - `private boolean emManutencao` — `true` quando em manutenção (movimentação e embarque/desembarque proibidos).

- Métodos obrigatórios (assinaturas mínimas):

  - `public void inicializar(int capacidade, int totalAndares)`
    - Pré-condições: `capacidade > 0`, `totalAndares >= 0`.
    - Pós-condições: `this.capacidade == capacidade`, `this.totalAndares == totalAndares`, `andarAtual == 0`, `pessoasPresentes == 0`, `emManutencao == false`.
  - `public void entrar()`
    - Somente se `!emManutencao` e `pessoasPresentes < capacidade`.
    - Se não puder entrar, manter estado inalterado.
  - `public void sair()`
    - Somente se `!emManutencao` e `pessoasPresentes > 0`.
    - Se não houver pessoas, manter estado inalterado.
  - `public void subir()`
    - Somente se `!emManutencao` e `andarAtual < totalAndares`.
    - Incrementa `andarAtual` em 1.
  - `public void descer()`
    - Somente se `!emManutencao` e `andarAtual > 0`.
    - Decrementa `andarAtual` em 1.
  - `public void colocarEmManutencao()`
    - Coloca `emManutencao = true` (opcional: zera movimentos; não altera `pessoasPresentes`).
  - `public void liberarManutencao()`
    - Coloca `emManutencao = false`.
  - Getters para todos os atributos: `getAndarAtual()`, `getTotalAndares()`, `getCapacidade()`, `getPessoasPresentes()`, `isEmManutencao()`.

- Invariantes / comportamentos esperados:

  - `0 <= andarAtual <= totalAndares`.
  - `0 <= pessoasPresentes <= capacidade`.
  - Enquanto `emManutencao == true` não é possível alterar `andarAtual` nem `pessoasPresentes`.

- Erros/retornos: as versões mínimas usam `void` e não lançam exceções; implementações podem optar por lançar `IllegalStateException` em pré-condições violadas ou retornar `boolean` indicando sucesso/fracasso.

- Sugestão de API (mais informativa):
  - `public boolean entrar()` — retorna `true` se a operação teve sucesso, `false` caso contrário.
  - `public boolean sair()` — idem.
  - `public boolean subir()` / `public boolean descer()` — idem.

### Predio (classe `Predio`) — (nome sugerido: `Predio`)

- Atributos obrigatórios:

  - `private String nome` — nome do prédio.
  - `private List<Elevador> elevadores` — lista de elevadores.

- Métodos obrigatórios:

  - `public Predio(String nome)` — construtor que inicializa `nome` e cria a lista vazia.
  - `public void adicionarElevador(Elevador elevador)` — adiciona o elevador na lista.
  - `public Elevador getElevador(int indice)` — retorna o elevador no índice (lança `IndexOutOfBoundsException` se índice inválido).
  - `public int getQuantidadeElevadores()` — retorna `elevadores.size()`.
  - `public String getNome()` — getter do nome.

- Regras / comportamentos:
  - Não aceitar `null` em `adicionarElevador` (lançar `NullPointerException` ou ignorar).
  - Operações sobre elevadores devem ser realizadas através de `ElevadorService` (recomendado).

### ElevadorService (classe `ElevadorService`)

- Responsabilidade: conter a lógica de regras de negócio, deixando o modelo (`Elevador`) apenas como porta de estado.

- Métodos obrigatórios (assinaturas mínimas):

  - `public void moverElevador(Elevador elevador, int destino)`
    - Move o elevador andar-a-andar até `destino` (0 <= destino <= totalAndares).
    - Deve respeitar `emManutencao` (se `true`, não deve mover).
    - Implementação mínima: enquanto `andarAtual < destino` chamar `elevador.subir()`, enquanto `andarAtual > destino` chamar `elevador.descer()`.
  - `public void embarcarPessoas(Elevador elevador, int quantidade)`
    - Tenta embarcar `quantidade` pessoas, respeitando `capacidade` e `emManutencao`.
    - Operação deve adicionar no máximo `capacidade - pessoasPresentes`.
  - `public void desembarcarPessoas(Elevador elevador, int quantidade)`
    - Remove até `quantidade` pessoas, sem permitir `pessoasPresentes < 0`.
  - `public String gerarStatus(Elevador elevador)`
    - Retorna uma String formatada com todas as informações relevantes (andar, pessoas, capacidade, modo de manutenção).

- Contrato / pré-condições:

  - `elevador` não pode ser `null` (lançar `NullPointerException`).
  - `destino` deve estar no intervalo `[0, elevador.getTotalAndares()]`.
  - `quantidade` deve ser >= 0; valores negativos devem ser ignorados ou lançar `IllegalArgumentException`.

- Comportamentos esperados:
  - Operações que alteram estado delegam para métodos de `Elevador` para preservar encapsulamento.
  - `gerarStatus` nunca altera o estado do elevador.

### Main (`app/main.java`) — execução e apresentação

- Responsabilidade: ser a única classe a interagir diretamente com o usuário (entrada/saída).
- Deveres:

  - Criar e inicializar objetos (`Predio`, `Elevador`, `ElevadorService`).
  - Executar uma sequência de operações para simular cenários (entrar/sair, subir/descer, manutenção).
  - Chamar `ElevadorService.gerarStatus(...)` e imprimir o resultado com `System.out.println` após cada operação.

- Regras de apresentação:
  - Toda saída textual do estado do sistema deve ocorrer em `Main`.
  - `Main` pode usar `Scanner` para entrada interativa, ou executar um script/simulação pré-definida.

## Requisitos não funcionais e boas práticas

- Separação de responsabilidades (SRP): modelos (`Elevador`, `Predio`) mantêm apenas estado e validação simples; regras de negócio ficam em `ElevadorService`; I/O em `Main`.
- Testabilidade: `ElevadorService` deve ser testável isoladamente (sem I/O). Prefira métodos com retorno informativo (boolean ou exceção) para facilitar asserts em testes.
- Robustez: validar parâmetros de entrada e documentar comportamento em casos inválidos.

## 🚀 Como compilar e executar (Windows PowerShell)

2. Crie um diretório de saída (opcional) e compile os arquivos Java (compila apenas os arquivos existentes):

   ```powershell
   mkdir -Force out; javac -d out app\main.java service\elevatorService.java model\*.java
   ```

3. Execute a aplicação:

   ```powershell
   java -cp out main
   ```

Observações:

- Se as classes estiverem em pacotes, ajuste os caminhos de compilação e o comando `java` para usar o nome qualificado da classe contendo `main`.
- Se algum arquivo `.java` estiver vazio, o compilador irá ignorá-lo; implemente as classes necessárias antes de executar para ver a simulação completa.

## ✅ Exemplo de saída esperada (simplificada)

Após cada operação o `main` deve imprimir algo como:

- "Elevador 1 — Andar: 2 | Pessoas: 3 | Capacidade: 6 | Status: Operacional"
- "Elevador 2 — Andar: 0 | Pessoas: 0 | Capacidade: 4 | Status: Em manutenção"

## Próximos passos (sugestões)

1. Implementar a classe `model/elevator.java` com os atributos e métodos descritos acima.
2. Criar uma classe `model/predio.java` para gerenciar múltiplos elevadores.
3. Implementar os métodos em `service/elevatorService.java` para realizar as operações e retornar o status formatado.
4. Preencher `app/main.java` com uma simulação que cria um prédio, adiciona elevadores e aplica operações (entrar/sair, subir/descer, manutenção), imprimindo o status após cada ação.

## Licença

Consulte o arquivo `LICENSE` no repositório.
