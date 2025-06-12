package q10_CaixeiroViajante;

import java.util.ArrayList;
import java.util.List;

public class PCVForcaBruta {

    static Cidade[] cidades;
    static int numeroCidades;
    static double menorDistancia = Double.MAX_VALUE;
    static int[] melhorRota;

    public static double calcularDistanciaEuclidiana(Cidade c1, Cidade c2) {
        return Math.sqrt(Math.pow(c2.obterX() - c1.obterX(), 2) + Math.pow(c2.obterY() - c1.obterY(), 2));
    }

    public static void resolverPCV(Cidade[] conjuntoCidades) {
        cidades = conjuntoCidades;
        numeroCidades = cidades.length;
        melhorRota = new int[numeroCidades + 1];

        int[] rotaAtual = new int[numeroCidades];
        boolean[] visitado = new boolean[numeroCidades];

        rotaAtual[0] = cidades[0].obterId();
        visitado[0] = true;

        gerarPermutacoes(1, rotaAtual, visitado);

        System.out.println("\n--- Resultado Final ---");
        System.out.printf("Menor distância encontrada: %.2f\n", menorDistancia);
        System.out.print("Melhor rota: ");
        for (int i = 0; i < melhorRota.length; i++) {
            System.out.print("C" + melhorRota[i]);
            if (i < melhorRota.length - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    private static void gerarPermutacoes(int indice, int[] rotaAtual, boolean[] visitado) {
        if (indice == numeroCidades) {
            double distanciaAtual = 0;
            List<String> cadeiaCidades = new ArrayList<>();
            List<Double> distanciasEntreCidades = new ArrayList<>();

            cadeiaCidades.add("C" + rotaAtual[0]);

            for (int i = 0; i < numeroCidades - 1; i++) {
                Cidade c1 = cidades[rotaAtual[i]];
                Cidade c2 = cidades[rotaAtual[i+1]];
                double dist = calcularDistanciaEuclidiana(c1, c2);
                distanciaAtual += dist;
                distanciasEntreCidades.add(dist);
                cadeiaCidades.add("C" + rotaAtual[i+1]);
            }

            Cidade ultimaCidade = cidades[rotaAtual[numeroCidades - 1]];
            Cidade primeiraCidade = cidades[rotaAtual[0]];
            double distVolta = calcularDistanciaEuclidiana(ultimaCidade, primeiraCidade);
            distanciaAtual += distVolta;
            distanciasEntreCidades.add(distVolta);
            cadeiaCidades.add("C" + rotaAtual[0]);

            System.out.println("\n--- Analisando Rota ---");
            System.out.print("Cadeia de cidades: ");
            for (int i = 0; i < cadeiaCidades.size(); i++) {
                System.out.print(cadeiaCidades.get(i));
                if (i < cadeiaCidades.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();

            System.out.print("Distâncias entre cidades: ");
            for (int i = 0; i < distanciasEntreCidades.size(); i++) {
                System.out.printf("%.2f", distanciasEntreCidades.get(i));
                if (i < distanciasEntreCidades.size() - 1) {
                    System.out.print(" + ");
                }
            }
            System.out.printf(" = %.2f\n", distanciaAtual);
            System.out.println("Distância total: " + String.format("%.2f", distanciaAtual));

            if (distanciaAtual < menorDistancia) {
                menorDistancia = distanciaAtual;
                System.arraycopy(rotaAtual, 0, melhorRota, 0, numeroCidades);
                melhorRota[numeroCidades] = rotaAtual[0];
            }

            return;
        }

        for (int i = 0; i < numeroCidades; i++) {
            if (!visitado[i]) {
                rotaAtual[indice] = cidades[i].obterId();
                visitado[i] = true;
                gerarPermutacoes(indice + 1, rotaAtual, visitado);
                visitado[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Cidade[] conjuntoCidades = new Cidade[10];
        conjuntoCidades[0] = new Cidade(0, 0, 0);
        conjuntoCidades[1] = new Cidade(1, 1, 5);
        conjuntoCidades[2] = new Cidade(2, 4, 1);
        conjuntoCidades[3] = new Cidade(3, 7, 6);
        conjuntoCidades[4] = new Cidade(4, 2, 8);
        conjuntoCidades[5] = new Cidade(5, 9, 3);
        conjuntoCidades[6] = new Cidade(6, 5, 9);
        conjuntoCidades[7] = new Cidade(7, 8, 0);
        conjuntoCidades[8] = new Cidade(8, 3, 4);
        conjuntoCidades[9] = new Cidade(9, 6, 2);

        resolverPCV(conjuntoCidades);
    }
}