package q8_SomaSubconjuntos;

import java.util.ArrayList;
import java.util.List;

public class SomaSubconjuntosBT {
    private int[] conjuntoOriginal;
    private int somaAlvo;
    private boolean encontrouSolucao;

    public SomaSubconjuntosBT(int[] conjunto, int alvo) {
        this.conjuntoOriginal = conjunto;
        this.somaAlvo = alvo;
        this.encontrouSolucao = false;
    }

    public boolean resolver() {
        List<Integer> subconjuntoAtual = new ArrayList<>();
        encontrarSubconjunto(0, 0, subconjuntoAtual);
        return encontrouSolucao;
    }

    private void encontrarSubconjunto(int indiceAtual, int somaAtual, List<Integer> subconjuntoAtual) {
        if (encontrouSolucao) {
            return;
        }

        if (somaAtual == somaAlvo && !subconjuntoAtual.isEmpty()) {
            System.out.println("!!! SOMA DEU ZERO EBAAAAAAAAA " + subconjuntoAtual);
            encontrouSolucao = true;
            return;
        }

        if (indiceAtual == conjuntoOriginal.length) {
            return;
        }

        subconjuntoAtual.add(conjuntoOriginal[indiceAtual]);
        System.out.println("Pegando: " + conjuntoOriginal[indiceAtual] + ". Subconjunto atual: " + subconjuntoAtual + ". Soma atual: " + (somaAtual + conjuntoOriginal[indiceAtual]));
        encontrarSubconjunto(indiceAtual + 1, somaAtual + conjuntoOriginal[indiceAtual], subconjuntoAtual);

        subconjuntoAtual.remove(subconjuntoAtual.size() - 1);
        System.out.println("BACKTRACK: Desfazendo escolha do item " + conjuntoOriginal[indiceAtual] + ". Subconjunto atual: " + subconjuntoAtual);
        System.out.println("Não pegando: " + conjuntoOriginal[indiceAtual] + ". Subconjunto atual: " + subconjuntoAtual + ". Soma atual: " + somaAtual);
        encontrarSubconjunto(indiceAtual + 1, somaAtual, subconjuntoAtual);
    }

    public static void main(String[] args) {
        int[] conjunto = {-7, -3, -2, 5, 8};
        int alvo = 0;

        SomaSubconjuntosBT problema = new SomaSubconjuntosBT(conjunto, alvo);
        System.out.println("Começo da busca:");
        if (!problema.resolver()) {
            System.out.println("NAO DEU ZERO AMIGO");
        } else {
            System.out.println("BUSCA ACABOU, UM OU MAIS SUBCONJUNTOS DERAM SOMA ZERO!");
        }
    }
}
