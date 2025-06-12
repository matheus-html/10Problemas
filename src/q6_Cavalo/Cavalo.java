package q6_Cavalo;

public class Cavalo {

    private int[][] tabuleiro;
    private int tamanho;

    private int[] moverX = {2, 1, -1, -2, -2, -1, 1, 2};
    private int[] moverY = {1, 2, 2, 1, -1, -2, -2, -1};

    public Cavalo(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new int[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public boolean resolverMovimento(int inicioX, int inicioY) {
        tabuleiro[inicioX][inicioY] = 1;

        if (encontrarMovimento(inicioX, inicioY, 2)) {
            System.out.println("Passeio do Cavalo Encontrado!");
            imprimirTabuleiro();
            return true;
        } else {
            System.out.println("Não foi possível encontrar um passeio do cavalo a partir desta posição inicial.");
            return false;
        }
    }

    private boolean encontrarMovimento(int atualX, int atualY, int contagemMovimento) {
        if (contagemMovimento > tamanho * tamanho) {
            return true;
        }

        for (int k = 0; k < 8; k++) {
            int proximoX = atualX + moverX[k];
            int proximoY = atualY + moverY[k];

            if (movimentoValido(proximoX, proximoY)) {
                tabuleiro[proximoX][proximoY] = contagemMovimento;

                if (encontrarMovimento(proximoX, proximoY, contagemMovimento + 1)) {
                    return true;
                }

                tabuleiro[proximoX][proximoY] = 0;
            }
        }
        return false;
    }

    private boolean movimentoValido(int x, int y) {
        return (x >= 0 && x < tamanho && y >= 0 && y < tamanho && tabuleiro[x][y] == 0);
    }

    private void imprimirTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.printf("%3d", tabuleiro[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int tamanhoTabuleiro = 8;
        Cavalo passeio = new Cavalo(tamanhoTabuleiro);

        int inicioLinha = 0;
        int inicioColuna = 0;

        if (!passeio.resolverMovimento(inicioLinha, inicioColuna)) {
            System.out.println("Nenhum passeio encontrado a partir de (" + inicioLinha + ", " + inicioColuna + ").");
        }
    }
}