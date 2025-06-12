package q4_MochilaLimitada;

import java.util.ArrayList;
import java.util.List;

public class MochilaPD {

    public static void resolverMochilaPD(Mochila mochila, Item[] itensDisponiveis) {
        int n = itensDisponiveis.length;
        int capacidadeMaxima = mochila.getCapacidadeMaxima();

        int matrizMochila [][] = new int[n + 1][capacidadeMaxima + 1];

        System.out.println("Matriz mochila:");

        for(int i=0; i<=n;i++){
            for(int j=0; j<=capacidadeMaxima;j++){
                if(i==0 || j == 0){
                    matrizMochila[i][j] = 0;
                } else {
                    Item itemAtual = itensDisponiveis[i - 1];

                    if(itemAtual.getPeso() <= j){
                        int valorSemItem = matrizMochila[i - 1][j];
                        int valorComItem = itemAtual.getValor() + matrizMochila[i-1][j-itemAtual.getPeso()];

                        matrizMochila[i][j] = Math.max(valorSemItem, valorComItem);

                        System.out.println("Matriz mochila["+i+"]["+j+"]: Avaliando item "+itemAtual.getNome() + "(P: "+itemAtual.getPeso()+") "+itemAtual.getPeso() + ", V: "+itemAtual.getValor());
                        System.out.println("    - Valor sem item: " + valorSemItem + " (de Matriz Mochila[" + (i-1) + "][" + j + "])");
                        System.out.println("    - Valor com item: " + valorComItem + " (item + Matriz Mochila[" + (i-1) + "][" + (j-itemAtual.getPeso()) + "])");
                        System.out.println("    - Escolha: " + matrizMochila[i][j]);
                    } else {
                        matrizMochila[i][j] = matrizMochila[i-1][j];
                        System.out.println(" Mochila Matriz["+i+"]["+j+"]: Item "+itemAtual.getNome() + "(P: "+itemAtual.getPeso()+")" +
                                "muito pesado para capacidade "+j+". Escolha: "+matrizMochila[i][j]);
                    }
                }
            }
        }

        System.out.println("Resultado matriz mochila: ");
        int valorTotalMaximo = matrizMochila[n][capacidadeMaxima];

        System.out.println("Recuperando os itens selecionados: ");
        List<Item> itensRecuperados = new ArrayList();
        int i = n;
        int j = capacidadeMaxima;

        while(i>0 && j>0){
            if(matrizMochila[i][j] != matrizMochila[i-1][j]){
                Item itemIncluido = itensDisponiveis[i-1];
                itensRecuperados.add(0, itemIncluido);
                j = j - itemIncluido.getPeso();

                System.out.println(" Item incluído: "+ itensDisponiveis[i-1].getNome() +
                        " não incluído.");
            } else {
                System.out.println(" Item "+ itensDisponiveis[i-1].getNome() + " não incluído.");
            }
            i--;
        }

        System.out.println("Itens selecionados:");
        for(Item item : itensRecuperados){
            mochila.adicionarItem(item);
            System.out.println("Item: "+item.getNome() + "(Peso: "+item.getPeso()+
                    "kg, Valor: R$"+item.getValor()+")");
        }

        System.out.println("Resultado final: ");
        System.out.println("Capacidade da mochila: "+mochila.getCapacidadeMaxima() + "kg");
        System.out.println("Peso total na mochila: "+mochila.getPesoAtual() + "kg");
        System.out.println("Valor total na mochila:  R$" + mochila.getValorAtual());
        System.out.println("Valor Máximo Esperado (da tabela Mochila Matriz): R$" + valorTotalMaximo);

    }

    public static void main(String[] args) {
        int capacidadeMochila = 15;

        Mochila mochila = new Mochila(capacidadeMochila);

        Item[] itens = new Item[7];

        itens[0] = new Item("Livro", 3, 30);
        itens[1] = new Item("Notebook", 5, 100);
        itens[2] = new Item("Camera", 2, 50);
        itens[3] = new Item("Garrafa de Agua", 1, 10);
        itens[4] = new Item("Tenis", 4, 45);
        itens[5] = new Item("Comida", 7, 90);
        itens[6] = new Item("Celular", 1, 70);

        resolverMochilaPD(mochila, itens);
    }
}
