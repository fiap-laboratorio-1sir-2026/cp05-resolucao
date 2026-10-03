package modeloB;

import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, i, j, k;
        int origem, destino;
        int menorCusto, origemMenor, destinoMenor;
        int custoDireto, custoTotal, menorEscala, centroEscala;
        int[][] custos;
        double soma, media;

        // entrada de dados
        System.out.print("Informe o total de centros de distribuição --> ");
        n = sc.nextInt();

        custos = new int[n][n];

        // ============= item a =============
        // preenchimento da matriz --> a diagonal principal permanece zero
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Custo de frete de CD" + (i + 1)
                            + " para CD" + (j + 1) + ": ");
                    custos[i][j] = sc.nextInt();
                }
            }
        }

        // impressão da matriz no formato tabular
        System.out.println("\n========== Matriz de custos de frete (reais): ==========");
        System.out.print("\t");
        for (j = 0; j < n; j++) {
            System.out.print("CD" + (j + 1) + "\t");
        }
        System.out.println();

        for (i = 0; i < n; i++) {
            System.out.print("CD" + (i + 1) + "\t");
            for (j = 0; j < n; j++) {
                System.out.print(custos[i][j] + "\t");
            }
            System.out.println();
        }

        // ============= item b =============
        // média dos custos de envio de cada centro
        System.out.println("\n========== Custo médio de envio por centro de origem: ==========");
        for (i = 0; i < n; i++) {
            soma = 0;
            for (j = 0; j < n; j++) {
                if (i != j) {
                    soma += custos[i][j];
                }
            }

            media = soma / (n - 1);
            System.out.println("CD" + (i + 1) + ": R$ "
                    + String.format("%.2f", media));
        }

        // ============= item c =============
        // frete mais barato --> o primeiro envio fora da diagonal é CD1 para CD2
        menorCusto = custos[0][1];
        origemMenor = 0;
        destinoMenor = 1;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j && custos[i][j] < menorCusto) {
                    menorCusto = custos[i][j];
                    origemMenor = i;
                    destinoMenor = j;
                }
            }
        }

        System.out.println("\n========== Frete mais barato ==========");
        System.out.println("Frete mais barato: CD" + (origemMenor + 1)
                + " para CD" + (destinoMenor + 1)
                + " (" + menorCusto + " reais)");

        // ============= item d =============
        // escolha dos centros
        System.out.print("\nCentro de origem (1 a " + n + "): ");
        origem = sc.nextInt();

        System.out.print("Centro de destino (1 a " + n + ", diferente da origem): ");
        destino = sc.nextInt();

        // conversão dos números dos centros para índices da matriz
        origem--;
        destino--;

        custoDireto = custos[origem][destino];
        System.out.println("\n========== Frete direto ==========");
        System.out.println(custoDireto + " reais");

        // busca da melhor rota com exatamente uma escala
        centroEscala = -1;
        menorEscala = 0;

        for (k = 0; k < n; k++) {
            if (k != origem && k != destino) {
                custoTotal = custos[origem][k] + custos[k][destino];
                if (centroEscala == -1 || custoTotal < menorEscala) {
                    menorEscala = custoTotal;
                    centroEscala = k;
                }
            }
        }

        if (centroEscala == -1) {
            System.out.println("Não existe rota com uma escala.");
        } else {
            System.out.println("Melhor rota com escala: via CD"
                    + (centroEscala + 1) + " ("
                    + menorEscala + " reais)");

            if (custoDireto < menorEscala) {
                System.out.println("O frete direto é mais barato.");
            } else if (menorEscala < custoDireto) {
                System.out.println("A rota com escala é mais barata.");
            } else {
                System.out.println("As duas opções têm o mesmo custo.");
            }
        }
    }
}
