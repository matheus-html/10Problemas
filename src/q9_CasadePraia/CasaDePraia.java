package q9_CasadePraia;

import java.util.ArrayList;
import java.util.List;

public class CasaDePraia {
    public static void otimizarLeituraGuloso(Pendrive pendrive, List<ArquivoPDF> arquivosDisponiveis) {
        System.out.println("Iniciando a otimização de leitura (Algoritmo Guloso)...");
        System.out.println("Capacidade do Pendrive: " + pendrive.obterCapacidadeMaximaMB() + " MB\n");

        for (int i = 0; i < arquivosDisponiveis.size() - 1; i++) {
            for (int j = 0; j < arquivosDisponiveis.size() - 1 - i; j++) {
                if (arquivosDisponiveis.get(j).obterProporcaoPaginasPorMB() < arquivosDisponiveis.get(j + 1).obterProporcaoPaginasPorMB()) {
                    ArquivoPDF temp = arquivosDisponiveis.get(j);
                    arquivosDisponiveis.set(j, arquivosDisponiveis.get(j + 1));
                    arquivosDisponiveis.set(j + 1, temp);
                }
            }
        }
        System.out.println("Arquivos ordenados por Páginas/MB (maior para o menor):");
        for (ArquivoPDF arq : arquivosDisponiveis) {
            System.out.printf("  - %s (Peso: %.2f MB, Páginas: %d, Proporção: %.2f páginas/MB)\n",
                    arq.obterNome(), arq.obterPesoMB(), arq.obterPaginas(), arq.obterProporcaoPaginasPorMB());
        }

        double capacidadeRestante = pendrive.obterCapacidadeMaximaMB();
        System.out.println("Selecionando arquivos:");

        for (ArquivoPDF arquivo : arquivosDisponiveis) {
            System.out.printf("  Analisando: %s (Peso: %.2f MB, Páginas: %d). Capacidade restante: %.2f MB.\n",
                    arquivo.obterNome(), arquivo.obterPesoMB(), arquivo.obterPaginas(), capacidadeRestante);

            if (capacidadeRestante >= arquivo.obterPesoMB()) {
                pendrive.adicionarArquivo(arquivo);
                capacidadeRestante -= arquivo.obterPesoMB();
                System.out.printf("    - ESCOLHIDO: %s. Capacidade restante agora: %.2f MB.\n",
                        arquivo.obterNome(), capacidadeRestante);
            } else {
                System.out.printf("    - IGNORADO: %s. Muito grande para a capacidade restante.\n", arquivo.obterNome());
            }
        }

        System.out.println("\n--- Resumo Final ---");
        System.out.println("Arquivos escolhidos para o Pendrive:");
        if (pendrive.obterArquivosAdicionados().isEmpty()) {
            System.out.println("  Nenhum arquivo pôde ser adicionado.");
        } else {
            for (ArquivoPDF arq : pendrive.obterArquivosAdicionados()) {
                System.out.println("  - " + arq.obterNome() + " (Páginas: " + arq.obterPaginas() + ", Peso: " + arq.obterPesoMB() + " MB)");
            }
        }
        System.out.printf("Peso total no Pendrive: %.2f MB\n", pendrive.obterPesoAtualMB());
        System.out.println("Total de páginas no Pendrive: " + pendrive.obterTotalPaginas() + " páginas");
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        double capacidadePendrive = 100.0;
        Pendrive meuPendrive = new Pendrive(capacidadePendrive);

        List<ArquivoPDF> arquivos = new ArrayList<>();
        arquivos.add(new ArquivoPDF("Algoritmos_Fundamentais", 500, 20));
        arquivos.add(new ArquivoPDF("Analise_de_Complexidade", 300, 15));
        arquivos.add(new ArquivoPDF("Estruturas_de_Dados", 700, 30));
        arquivos.add(new ArquivoPDF("Grafos_e_Buscas", 400, 25));
        arquivos.add(new ArquivoPDF("Programacao_Dinamica", 200, 10));
        arquivos.add(new ArquivoPDF("Otimizacao_Combinatoria", 600, 40));
        arquivos.add(new ArquivoPDF("Backtracking_Avancado", 150, 5));

        otimizarLeituraGuloso(meuPendrive, arquivos);
    }
}
