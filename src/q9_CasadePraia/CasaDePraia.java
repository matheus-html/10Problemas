package q9_CasadePraia;

import java.util.ArrayList;
import java.util.List;

public class CasaDePraia {

    public static void otimizarLeituraGuloso(Pendrive pendrive, List<ArquivoPDF> arquivosDisponiveis) {
        System.out.println("Otimização:");
        System.out.printf("Capacidade do Pendrive: %.2f MB\n\n", pendrive.obterCapacidadeMaximaMB());

        for (int i = 0; i < arquivosDisponiveis.size() - 1; i++) {
            for (int j = 0; j < arquivosDisponiveis.size() - 1 - i; j++) {
                if (arquivosDisponiveis.get(j).obterProporcaoPaginasPorMB() < arquivosDisponiveis.get(j + 1).obterProporcaoPaginasPorMB()) {
                    ArquivoPDF temp = arquivosDisponiveis.get(j);
                    arquivosDisponiveis.set(j, arquivosDisponiveis.get(j + 1));
                    arquivosDisponiveis.set(j + 1, temp);
                }
            }
        }

        System.out.println("Ordenação por proporção (Páginas/MB)");
        for (ArquivoPDF arq : arquivosDisponiveis) {
            System.out.printf("  - %s (Proporção: %.2f páginas/MB)\n",
                    arq.obterNome(), arq.obterProporcaoPaginasPorMB());
        }
        System.out.println();

        System.out.println("Seleção de Arquivos");
        double capacidadeRestante = pendrive.obterCapacidadeMaximaMB();
        for (ArquivoPDF arquivo : arquivosDisponiveis) {
            if (capacidadeRestante >= arquivo.obterPesoMB()) {
                pendrive.adicionarArquivo(arquivo);
                capacidadeRestante -= arquivo.obterPesoMB();
                System.out.printf("  [✓] Adicionado: %s (%.2f MB). Espaço restante: %.2f MB\n",
                        arquivo.obterNome(), arquivo.obterPesoMB(), capacidadeRestante);
            } else {
                System.out.printf("  [✗] Ignorado: %s (%.2f MB) - Excede a capacidade.\n",
                        arquivo.obterNome(), arquivo.obterPesoMB());
            }
        }
        System.out.println();

        System.out.println("--- Passo 3: Resultado Final ---");
        System.out.println("Arquivos escolhidos:");
        if (pendrive.obterArquivosAdicionados().isEmpty()) {
            System.out.println("  Nenhum arquivo coube no pendrive.");
        } else {
            for (ArquivoPDF arq : pendrive.obterArquivosAdicionados()) {
                System.out.printf("  - %s (%d pág, %.2f MB)\n", arq.obterNome(), arq.obterPaginas(), arq.obterPesoMB());
            }
        }
        System.out.printf("\nPeso total usado: %.2f MB\n", pendrive.obterPesoAtualMB());
        System.out.printf("Total de páginas: %d\n", pendrive.obterTotalPaginas());
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