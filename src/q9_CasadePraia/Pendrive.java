package q9_CasadePraia;

import java.util.ArrayList;
import java.util.List;

public class Pendrive {
    private double capacidadeMaximaMB;
    private List<ArquivoPDF> arquivosAdicionados;

    public Pendrive(double capacidadeMaximaMB) {
        this.capacidadeMaximaMB = capacidadeMaximaMB;
        this.arquivosAdicionados = new ArrayList<>();
    }

    public double obterCapacidadeMaximaMB() {
        return capacidadeMaximaMB;
    }

    public List<ArquivoPDF> obterArquivosAdicionados() {
        return arquivosAdicionados;
    }

    public void adicionarArquivo(ArquivoPDF arquivo) {
        this.arquivosAdicionados.add(arquivo);
    }

    public double obterPesoAtualMB() {
        double peso = 0;
        for (ArquivoPDF arquivo : arquivosAdicionados) {
            peso += arquivo.obterPesoMB();
        }
        return peso;
    }

    public int obterTotalPaginas() {
        int paginas = 0;
        for (ArquivoPDF arquivo : arquivosAdicionados) {
            paginas += arquivo.obterPaginas();
        }
        return paginas;
    }
}
