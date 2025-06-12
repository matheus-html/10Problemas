package q7_Subconjuntos;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    public static void main(String[] args) {
        int [] conjunto = {-7, -3, -2, 5, 8};
        encontrarSubSomaZero(conjunto);
    }

    public static boolean encontrarSubSomaZero(int[] conjunto){
        int n = conjunto.length;
        boolean encontrado = false;

        System.out.println("Começo da busca:");

        // 1 << n é a mesma coisa de simplesmente 2^n
        for(int i = 0; i < (1 << n); i++){
            //isso é uma lista do subconjunto atual
            List<Integer> subconjuntoAtual = new ArrayList<>();

            //a soma atual
            int somaAtual = 0;

            System.out.println("\nAnalisando a combinação #"+i);

            //esse loop percorre o meu conjunto da main
            for(int j = 0; j < n; j++){

                // i percorre para a direita empurrando os bits para j posições
                if(((i >> j) & 1) == 1){

                    subconjuntoAtual.add(conjunto[j]);

                    somaAtual = somaAtual + conjunto[j];

                    System.out.println(" Pegando: " + conjunto[j] +
                            ". Soma até agora: " + somaAtual);
                }
            }
            System.out.print("Subconjunto montado: ");

            if(subconjuntoAtual.isEmpty()){
                System.out.println("{} vazio.");
            } else {
                System.out.println(subconjuntoAtual);
                System.out.println("Soma total: "+somaAtual);

                if(somaAtual == 0){
                    System.out.println("!!! SOMA DEU ZERO EBAAAAAAAAA " + subconjuntoAtual);
                    encontrado = true;
                }
            }
        }

        if(!encontrado){
            System.out.println("NAO DEU ZERO AMIGO");
        } else{
            System.out.println("BUSCA ACABOU, UM OU MAIS SUBCONJUNTOS DERAM SOMA ZERO!");
        }
        return encontrado;
    }
}
