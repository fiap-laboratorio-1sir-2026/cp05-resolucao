package modeloA;

import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, i, j, k;
        int origem, destino;
        int menorTempo, origemMenor, destinoMenor;
        int tempoDireto, tempoTotal, menorConexao, cidadeConexao;
        int[][] tempos;
        double soma, media;

        // entrada de dados
        System.out.print("Informe o total de cidades --> ");
        n = sc.nextInt();

        tempos = new int[n][n];

        // ============= item a =============
        // preenchimento da matriz --> a diagonal principal permanece zero (conforme enunciado)
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Tempo de voo de C" + (i + 1) + " para C" + (j + 1) + ": ");
                    tempos[i][j] = sc.nextInt();
                }
            }
        }

        // impressão da matriz no fomato tabular
        System.out.println("\n========== Matriz de tempos de voo (minutos): ==========" );
        System.out.print("\t");
        for (j = 0; j < n; j++) {
            System.out.print("C" + (j + 1) + "\t");
        }
        System.out.println();

        for (i = 0; i < n; i++) {
            System.out.print("C" + (i + 1) + "\t");
            for (j = 0; j < n; j++) {
                System.out.print(tempos[i][j] + "\t");
            }
            System.out.println();
        }

        // ============= item b =============
        // média dos voos de cada cidade
        System.out.println("\n========== Tempo médio de voo por cidade de origem: ==========");
        for (i = 0; i < n; i++) {
            soma = 0;
            for (j = 0; j < n; j++) {
                if (i != j) {
                    soma += tempos[i][j];
                }
            }

            media = soma / (n - 1);
            System.out.println("C" + (i + 1) + ": " + String.format("%.2f", media) + " minutos");
        }

        // ============= item c =============
        // voo mais curto --> o primeiro voo fora da diagonal é C1 para C2.
        menorTempo = tempos[0][1];
        origemMenor = 0;
        destinoMenor = 1;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j && tempos[i][j] < menorTempo) {
                    menorTempo = tempos[i][j];
                    origemMenor = i;
                    destinoMenor = j;
                }
            }
        }

        System.out.println("\n========== Voo mais curto ==========");
        System.out.println("Voo mais curto: C" + (origemMenor + 1)
                + " para C" + (destinoMenor + 1)
                + " (" + menorTempo + " minutos)");

        // ============= item d =============
        // escolha das cidades
        System.out.print("\nCidade de origem (1 a " + n + "): ");
        origem = sc.nextInt();

        System.out.print("Cidade de destino (1 a " + n + ", diferente da origem): ");
        destino = sc.nextInt();

        // conversão dos números das cidades para índices da matriz
        origem--;
        destino--;

        tempoDireto = tempos[origem][destino];
        System.out.println("\n========== Voo direto ==========");
        System.out.println(tempoDireto + " minutos");

        // busca da melhor rota com exatamente uma conexão
        cidadeConexao = -1;
        menorConexao = 0;

        for (k = 0; k < n; k++) {
            if (k != origem && k != destino) {
                tempoTotal = tempos[origem][k] + tempos[k][destino];
                if (cidadeConexao == -1 || tempoTotal < menorConexao) {
                    menorConexao = tempoTotal;
                    cidadeConexao = k;
                }
            }
        }

        if (cidadeConexao == -1) {
            System.out.println("Não existe rota com uma conexão.");
        } else {
            System.out.println("Melhor conexão: via C"
                    + (cidadeConexao + 1) + " ("
                    + menorConexao + " minutos)");

            if (tempoDireto < menorConexao) {
                System.out.println("O voo direto é mais rápido.");
            } else if (menorConexao < tempoDireto) {
                System.out.println("A rota com conexão é mais rápida.");
            } else {
                System.out.println("As duas opções têm o mesmo tempo.");
            }
        }
    }
}