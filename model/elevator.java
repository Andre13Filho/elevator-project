package model;

public class Elevador {
    private int andarAtual = 0;
    private int totalAndares;
    private int capacidade;
    private int pessoasPresentes = 0;
    private boolean emManutencao = false;

    public void inicializar(int capacidade, int totalAndares) {
        if (capacidade <= 0 || totalAndares < 0) {
            throw new IllegalArgumentException("Valores inválidos");
        }
        this.capacidade = capacidade;
        this.totalAndares = totalAndares;
        this.andarAtual = 0;
        this.pessoasPresentes = 0;
        this.emManutencao = false;
    }

    public boolean entrar() {
        if (emManutencao || pessoasPresentes >= capacidade) {
            return false;
        }
        pessoasPresentes++;
        System.out.println("Mais uma pessoa entrou no elevador");
        return true;
    }

    public boolean sair() {
        if (emManutencao || pessoasPresentes <= 0) {
            return false;
        }
        pessoasPresentes--;
        return true;
    }

    public boolean subir() {
        if (emManutencao || andarAtual >= totalAndares) {
            return false;
        }
        andarAtual++;
        return true;
    }

    public boolean descer() {
        if (emManutencao || andarAtual <= 0) {
            return false;
        }
        andarAtual--;
        return true;
    }

    public void colocarEmManutencao() {
        emManutencao = true;
    }

    public void liberarManutencao() {
        emManutencao = false;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public int getPessoasPresentes() {
        return pessoasPresentes;
    }

    public int getTotalAndares() {
        return totalAndares;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isEmManutencao() {
        return emManutencao;
    }
}