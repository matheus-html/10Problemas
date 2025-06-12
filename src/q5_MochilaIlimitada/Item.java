package q5_MochilaIlimitada;

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

    public void setProporcao(double proporcao) {
        this.proporcao = proporcao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
}
