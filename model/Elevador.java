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

    public void entrar() {
        if (emManutencao || pessoasPresentes >= capacidade) {
            System.out.println("Limite de pessoas excedido ou elevador está em Manutenção!");
            return;
        }
        else{
        this.pessoasPresentes++;
        System.out.println("Uma pessoa entrou no elevador");  
                }
    }

    public void sair() {
        if (emManutencao || pessoasPresentes <= 0) { 
            System.out.println("Elevador está vazio ou está em Manutenção");
            return;
        }
        else{
        this.pessoasPresentes--;
        System.out.println("Uma pessoa saiu do elevador");
        }
    }

    public void subir() {
        if (andarAtual >= totalAndares) {
            System.out.println("Elevador está no ultimo andar");
        }
        else{
            System.out.println("Elevador subindo para o andar " + (this.andarAtual + 1));
        this.andarAtual++;
        }
        }

    public void descer() {
        if (andarAtual <= 0) {
            System.out.println("Elevador está no terreo");
            return;
        }
        System.out.println("Elevador descendo para o andar " + (this.andarAtual - 1));
        this.andarAtual--;
    }

    public void colocarEmManutencao() {
        this.emManutencao = true;
    }

    public void liberarManutencao() {
        this.emManutencao = false;
    }

    public int getAndarAtual() {
        return this.andarAtual;
    }

    public int getPessoasPresentes() {
        return this.pessoasPresentes;
    }

    public int getTotalAndares() {
        return this.totalAndares;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public boolean isEmManutencao() {
        return this.emManutencao;
    }
}
