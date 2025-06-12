package q5_MochilaIlimitada;

import java.util.ArrayList;
import java.util.List;

public class Mochila {
    private int capacidadeMaxima;
    private List<Item> itensAdicionados;

    public Mochila(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.itensAdicionados = new ArrayList<>();
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void adicionarItem(Item item) {
        this.itensAdicionados.add(item);
    }

    public int getPesoAtual() {
        int peso = 0;
        for (Item item : itensAdicionados) {
            peso += item.getPeso();
        }
        return peso;
    }

    public int getValorAtual() {
        int valor = 0;
        for (Item item : itensAdicionados) {
            valor += item.getValor();
        }
        return valor;
    }
}