package q2_NDamas;

import java.util.Scanner;

public class Ndamas {
    private int[] tabuleiro;
    private int tamanhoTabuleiro;

    public Ndamas(int tamanhoTabuleiro){
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        tabuleiro = new int[tamanhoTabuleiro];
    }

    public boolean resolverDama(){
        return colocarDama(0);
    }

    private boolean colocarDama(int linhaAtual){
        if(linhaAtual == tamanhoTabuleiro){
            return true;
        }

        for(int coluna = 0; coluna < tamanhoTabuleiro; coluna++){
            if(ehSeguro(linhaAtual, coluna)){
                tabuleiro[linhaAtual] = coluna;

                if(colocarDama(linhaAtual+1)){
                    return true;
                }
                System.out.println("BACKTRACK: Voltando da linha " + (linhaAtual + 1) + " para a linha " + linhaAtual + ". Tentando nova posição para dama na linha " + linhaAtual + ".");
            }
        }
        return false;
    }

    private boolean ehSeguro(int linha, int coluna){
        for(int i=0; i<linha; i++){
            if(tabuleiro[i] == coluna){
                return false;
            }

            if (Math.abs(linha - i) == Math.abs(coluna - tabuleiro[i])) {
                return false;
            }
        }
        return true;
    }

    private void imprimirTabuleiro(){
        System.out.println("Solução: ");

        for(int i = 0; i<tamanhoTabuleiro;i++){
            for(int j = 0; j<tamanhoTabuleiro; j++){
                if(tabuleiro[i] == j){
                    System.out.print(" D ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a quantidade de damas: ");
        int n = sc.nextInt();

        Ndamas problema = new Ndamas(n);

        if(problema.resolverDama()){
            problema.imprimirTabuleiro();
        } else {
            System.out.println("Nenhum solução encontrada!!!!");
        }
    }
}
