package modeloD;

import java.util.Scanner;

public class Exercicio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, i, j, k;
        int partida, chegada;
        int menorConsumo, partidaMenor, chegadaMenor;
        int consumoDireto, consumoTotal, menorParada, portoParada;
        int[][] consumos;
        double soma, media;

        // entrada de dados
        System.out.print("Informe o total de portos --> ");
        n = sc.nextInt();

        consumos = new int[n][n];

        // ============= item a =============
        // preenchimento da matriz --> a diagonal principal permanece zero
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("Consumo de combustível de P" + (i + 1)
                            + " para P" + (j + 1) + ": ");
                    consumos[i][j] = sc.nextInt();
                }
            }
        }

        // impressão da matriz no formato tabular
        System.out.println("\n========== Matriz de consumo de combustível (litros): ==========");
        System.out.print("\t");
        for (j = 0; j < n; j++) {
            System.out.print("P" + (j + 1) + "\t");
        }
        System.out.println();

        for (i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + "\t");
            for (j = 0; j < n; j++) {
                System.out.print(consumos[i][j] + "\t");
            }
            System.out.println();
        }

        // ============= item b =============
        // média do consumo das viagens de cada porto
        System.out.println("\n========== Consumo médio por porto de partida: ==========");
        for (i = 0; i < n; i++) {
            soma = 0;
            for (j = 0; j < n; j++) {
                if (i != j) {
                    soma += consumos[i][j];
                }
            }

            media = soma / (n - 1);
            System.out.println("P" + (i + 1) + ": "
                    + String.format("%.2f", media) + " litros");
        }

        // ============= item c =============
        // trecho mais econômico --> o primeiro trecho fora da diagonal é P1 para P2
        menorConsumo = consumos[0][1];
        partidaMenor = 0;
        chegadaMenor = 1;

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i != j && consumos[i][j] < menorConsumo) {
                    menorConsumo = consumos[i][j];
                    partidaMenor = i;
                    chegadaMenor = j;
                }
            }
        }

        System.out.println("\n========== Trecho mais econômico ==========");
        System.out.println("Trecho mais econômico: P" + (partidaMenor + 1)
                + " para P" + (chegadaMenor + 1)
                + " (" + menorConsumo + " litros)");

        // ============= item d =============
        // escolha dos portos
        System.out.print("\nPorto de partida (1 a " + n + "): ");
        partida = sc.nextInt();

        System.out.print("Porto de chegada (1 a " + n + ", diferente da partida): ");
        chegada = sc.nextInt();

        // conversão dos números dos portos para índices da matriz
        partida--;
        chegada--;

        consumoDireto = consumos[partida][chegada];
        System.out.println("\n========== Viagem direta ==========");
        System.out.println(consumoDireto + " litros");

        // busca da melhor rota com exatamente uma parada
        portoParada = -1;
        menorParada = 0;

        for (k = 0; k < n; k++) {
            if (k != partida && k != chegada) {
                consumoTotal = consumos[partida][k] + consumos[k][chegada];
                if (portoParada == -1 || consumoTotal < menorParada) {
                    menorParada = consumoTotal;
                    portoParada = k;
                }
            }
        }

        if (portoParada == -1) {
            System.out.println("Não existe rota com uma parada.");
        } else {
            System.out.println("Melhor rota com uma parada: via P"
                    + (portoParada + 1) + " ("
                    + menorParada + " litros)");

            if (consumoDireto < menorParada) {
                System.out.println("A viagem direta é mais econômica.");
            } else if (menorParada < consumoDireto) {
                System.out.println("A rota com parada é mais econômica.");
            } else {
                System.out.println("As duas opções têm o mesmo consumo.");
            }
        }
    }
}