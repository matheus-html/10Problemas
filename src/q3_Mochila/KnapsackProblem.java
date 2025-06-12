package q3_Mochila;

public class KnapsackProblem {

    public static void resolverMochilaGuloso(Mochila mochila, Item[] itensDisponiveis) {

        for (int i = 0; i < itensDisponiveis.length - 1; i++) {
            for (int j = 0; j < itensDisponiveis.length - 1 - i; j++) {
                if (itensDisponiveis[j].getProporcao() < itensDisponiveis[j + 1].getProporcao()) {

                    Item temp = itensDisponiveis[j];
                    itensDisponiveis[j] = itensDisponiveis[j + 1];
                    itensDisponiveis[j + 1] = temp;
                }
            }
        }

        System.out.println("Itens selecionados:");

        for (Item item : itensDisponiveis) {
            if (mochila.getPesoAtual() + item.getPeso() <= mochila.getCapacidadeMaxima()) {
                mochila.adicionarItem(item);
                System.out.println("- " + item.getNome() + " (Peso: " + item.getPeso() + "kg, Valor: R$" + item.getValor() + ")");
            }
        }

        System.out.println("\nResultado:");
        System.out.println("Capacidade da mochila: " + mochila.getCapacidadeMaxima() + "kg");
        System.out.println("Peso total na mochila: " + mochila.getPesoAtual() + "kg");
        System.out.println("Valor total na mochila: R$" + mochila.getValorAtual());
    }

    public static void main(String[] args) {
        int capacidadeMochila = 15;
        Mochila minhaMochila = new Mochila(capacidadeMochila);

        Item[] itens = new Item[7];

        itens[0] = new Item("Livro", 3, 30);
        itens[1] = new Item("Notebook", 5, 100);
        itens[2] = new Item("Camera", 2, 50);
        itens[3] = new Item("Garrafa de Agua", 1, 10);
        itens[4] = new Item("Tenis", 4, 45);
        itens[5] = new Item("Comida", 7, 90);
        itens[6] = new Item("Celular", 1, 70);

        resolverMochilaGuloso(minhaMochila, itens);
    }
}

