package q3_Mochila;

public class Item {
    private String nome;
    private int peso;
    private int valor;
    private double proporcao;

    public Item(String nome, int peso, int valor) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        if (peso != 0) {
            this.proporcao = (double) valor / peso;
        } else {
            this.proporcao = 0;
        }
    }

    public double getProporcao() {
        return proporcao;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

}
