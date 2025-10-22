import model.Predio;
import model.Elevador;

public class Main {
    public static void main(String[] args) {
        Predio predio = new Predio("Prédio UNIUBE");

        Elevador elevador = new Elevador();
        elevador.inicializar(5, 10); // capacidade 5, 10 andares (0..10)
        predio.adicionarElevador(elevador);

        ElevadorService service = new ElevadorService();

        System.out.println("Inicial: " + service.gerarStatus(elevador));

        // Embarcar 3 pessoas
        service.embarcarPessoas(elevador, 3);
        System.out.println("Após embarcar 3: " + service.gerarStatus(elevador));

        // Mover para o 5º andar
        service.moverElevador(elevador, 5);
        System.out.println("Após mover para 5: " + service.gerarStatus(elevador));

        // Desembarcar 2 pessoas
        service.desembarcarPessoas(elevador, 2);
        System.out.println("Após desembarcar 2: " + service.gerarStatus(elevador));

        // Colocar em manutenção e tentar mover (não deve mover)
        elevador.colocarEmManutencao();
        System.out.println("Após colocar em manutenção: " + service.gerarStatus(elevador));

        service.moverElevador(elevador, 2); // operação deve ser ignorada
        System.out.println("Tentativa mover em manutenção (sem mudança): " + service.gerarStatus(elevador));

        // Liberar manutenção e mover para o térreo
        elevador.liberarManutencao();
        service.moverElevador(elevador, 0);
        System.out.println("Após liberar manutenção e mover para 0: " + service.gerarStatus(elevador));
    }
}