package q5_MochilaIlimitada;

import java.util.ArrayList;
import java.util.List;

public class MochilaIlimitada {
    public static void resolverMochilaIlimitadaPD(Mochila mochila, Item[] itensDisponiveis) {
        int capacidade = mochila.getCapacidadeMaxima();
        int[] dp = new int[capacidade + 1];

        for (int j = 1; j <= capacidade; j++) {
            for (Item item : itensDisponiveis) {
                if (item.getPeso() <= j) {
                    dp[j] = Math.max(dp[j], item.getValor() + dp[j - item.getPeso()]);
                }
            }
        }

        List<Item> itensSelecionados = new ArrayList<>();
        int pesoRestante = capacidade;
        int valorMaximoAlcancado = dp[capacidade];

        System.out.println("Processo de seleção de itens (Programação Dinâmica - Ilimitada):");

        while (pesoRestante > 0 && valorMaximoAlcancado > 0) {
            boolean itemEncontrado = false;
            for (Item item : itensDisponiveis) {
                if (pesoRestante >= item.getPeso() && (pesoRestante - item.getPeso() >= 0) &&
                        valorMaximoAlcancado == dp[pesoRestante - item.getPeso()] + item.getValor()) {
                    itensSelecionados.add(item);
                    mochila.adicionarItem(item);
                    System.out.println("  ESCOLHA FEITA: Item '" + item.getNome() + "' (Peso: " + item.getPeso() + "kg, Valor: R$" + item.getValor() + ") foi ADICIONADO.");
                    valorMaximoAlcancado -= item.getValor();
                    pesoRestante -= item.getPeso();
                    itemEncontrado = true;
                    break;
                }
            }
            if (!itemEncontrado && valorMaximoAlcancado > 0) {
                break;
            }
        }

        System.out.println("\nItens selecionados para a mochila (lista final):");
        if (itensSelecionados.isEmpty() && dp[capacidade] > 0) {
            System.out.println("Não foi possível rastrear individualmente, mas o valor máximo é R$" + dp[capacidade] + ".");
        } else {
            for (Item item : itensSelecionados) {
                System.out.println("- " + item.getNome());
            }
        }

        System.out.println("\n--- Resumo ---");
        System.out.println("Capacidade da mochila: " + mochila.getCapacidadeMaxima() + "kg");
        System.out.println("Peso total na mochila: " + mochila.getPesoAtual() + "kg");
        System.out.println("Valor total na mochila: R$" + mochila.getValorAtual());
        System.out.println("Valor máximo possível (calculado pela PD): R$" + dp[capacidade]);
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

        resolverMochilaIlimitadaPD(minhaMochila, itens);
    }
}
