import model.Elevador;

public class ElevadorService {

    public void moverElevador(Elevador elevador, int destino) {
        if (elevador == null) throw new NullPointerException("elevador == null");

        if (destino < 0 || destino > elevador.getTotalAndares())
            throw new IllegalArgumentException("destino fora do intervalo");

        if (elevador.isEmManutencao()) return;

        while (elevador.getAndarAtual() < destino) {
            elevador.subir();
        }
        while (elevador.getAndarAtual() > destino) {
            elevador.descer();
        }
    }

    public void embarcarPessoas(Elevador elevador, int quantidade) {
        if (elevador == null) throw new NullPointerException("elevador == null");
        if (quantidade < 0) throw new IllegalArgumentException("quantidade negativa");
        if (elevador.isEmManutencao()) return;

        int disponivel = elevador.getCapacidade() - elevador.getPessoasPresentes();
        int toAdd = Math.min(disponivel, quantidade);
        for (int i = 0; i < toAdd; i++) elevador.entrar();
    }

    public void desembarcarPessoas(Elevador elevador, int quantidade) {
        if (elevador == null) throw new NullPointerException("elevador == null");
        if (quantidade < 0) throw new IllegalArgumentException("quantidade negativa");
        if (elevador.isEmManutencao()) return;

        int toRemove = Math.min(elevador.getPessoasPresentes(), quantidade);
        for (int i = 0; i < toRemove; i++) elevador.sair();
    }

    public String gerarStatus(Elevador elevador) {
        if (elevador == null) throw new NullPointerException("elevador == null");
        return String.format("Andar: %d | Pessoas: %d/%d | Manutenção: %b",
                elevador.getAndarAtual(), elevador.getPessoasPresentes(), elevador.getCapacidade(), elevador.isEmManutencao());
    }
}
