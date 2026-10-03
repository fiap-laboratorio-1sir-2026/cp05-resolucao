package modeloC;

import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, i, j, k;
        int origem, destino;
        int menorLatencia, origemMenor, destinoMenor;
        int latenciaDireta, latenciaTotal, menorRota, servidorIntermediario;
        int[][] latencias;
        double soma, media;

        // entrada de dados
        System.out.print("Informe o total de servidores --> ");
        n = sc.nextInt();

        latencias = new int[n][n];

        // ============= item a =============
        // preenchimento da matriz --> a diagonal principal permanece zero
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Latência de S" + (i + 1)
                            + " para S" + (j + 1) + ": ");
                    latencias[i][j] = sc.nextInt();
                }
            }
        }

        // impressão da matriz no formato tabular
        System.out.println("\n========== Matriz de latências (ms): ==========");
        System.out.print("\t");
        for (j = 0; j < n; j++) {
            System.out.print("S" + (j + 1) + "\t");
        }
        System.out.println();

        for (i = 0; i < n; i++) {
            System.out.print("S" + (i + 1) + "\t");
            for (j = 0; j < n; j++) {
                System.out.print(latencias[i][j] + "\t");
            }
            System.out.println();
        }

        // ============= item b =============
        // média das latências de saída de cada servidor
        System.out.println("\n========== Latência média de saída por servidor: ==========");
        for (i = 0; i < n; i++) {
            soma = 0;
            for (j = 0; j < n; j++) {
                if (i != j) {
                    soma += latencias[i][j];
                }
            }

            media = soma / (n - 1);
            System.out.println("S" + (i + 1) + ": "
                    + String.format("%.2f", media) + " ms");
        }

        // ============= item c =============
        // enlace mais rápido --> o primeiro enlace fora da diagonal é S1 para S2
        menorLatencia = latencias[0][1];
        origemMenor = 0;
        destinoMenor = 1;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j && latencias[i][j] < menorLatencia) {
                    menorLatencia = latencias[i][j];
                    origemMenor = i;
                    destinoMenor = j;
                }
            }
        }

        System.out.println("\n========== Enlace mais rápido ==========");
        System.out.println("Enlace mais rápido: S" + (origemMenor + 1)
                + " para S" + (destinoMenor + 1)
                + " (" + menorLatencia + " ms)");

        // ============= item d =============
        // escolha dos servidores
        System.out.print("\nServidor de origem (1 a " + n + "): ");
        origem = sc.nextInt();

        System.out.print("Servidor de destino (1 a " + n + ", diferente da origem): ");
        destino = sc.nextInt();

        // conversão dos números dos servidores para índices da matriz
        origem--;
        destino--;

        latenciaDireta = latencias[origem][destino];
        System.out.println("\n========== Enlace direto ==========");
        System.out.println(latenciaDireta + " ms");

        // busca da melhor rota passando por um servidor intermediário
        servidorIntermediario = -1;
        menorRota = 0;

        for (k = 0; k < n; k++) {
            if (k != origem && k != destino) {
                latenciaTotal = latencias[origem][k] + latencias[k][destino];
                if (servidorIntermediario == -1 || latenciaTotal < menorRota) {
                    menorRota = latenciaTotal;
                    servidorIntermediario = k;
                }
            }
        }

        if (servidorIntermediario == -1) {
            System.out.println("Não existe rota com um servidor intermediário.");
        } else {
            System.out.println("Melhor rota com um salto: via S"
                    + (servidorIntermediario + 1) + " ("
                    + menorRota + " ms)");

            if (latenciaDireta < menorRota) {
                System.out.println("O enlace direto é mais rápido.");
            } else if (menorRota < latenciaDireta) {
                System.out.println("A rota com um salto é mais rápida.");
            } else {
                System.out.println("As duas opções têm a mesma latência.");
            }
        }
    }
}