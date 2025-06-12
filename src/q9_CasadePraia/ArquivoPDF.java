package q9_CasadePraia;

public class ArquivoPDF {
    private String nome;
    private int paginas;
    private int pesoMB;
    private double proporcaoPaginasPorMB;

    public ArquivoPDF(String nome, int paginas, int pesoMB) {
        this.nome = nome;
        this.paginas = paginas;
        this.pesoMB = pesoMB;
        if (pesoMB > 0) {
            this.proporcaoPaginasPorMB = (double) paginas / pesoMB;
        } else {
            this.proporcaoPaginasPorMB = 0;
        }
    }

    public String obterNome() {
        return nome;
    }

    public int obterPaginas() {
        return paginas;
    }

    public double obterPesoMB() {
        return pesoMB;
    }

    public double obterProporcaoPaginasPorMB() {
        return proporcaoPaginasPorMB;
    }
}
