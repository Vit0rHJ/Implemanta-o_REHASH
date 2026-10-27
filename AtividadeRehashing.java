//implementação com a inserção de 100 elementos em uma matriz de 100 posições,.
//Quando a matriz chegar em 90% da ocupação o re hash deve impedir a inserção de novos elementos.
//Utilize random para gerar numeros aleatórios para completar a matriz.
//Use seed = 1090.
//Ao fim, conte o número total de colisões de elementos na inserção, os 3 elementos que mais colidiram até a inserção e quais posições ficaram livres na matriz;
//Sem a utilização de comandos prontos da api do java, apenas, if, else, for, while e random.

import java.util.Random;

public class AtividadeRehashing {
    public static void main(String[] args) {
        // Define o tamanho da tabela, o limite de ocupação e as variáveis.
        int tamanho = 100;
        int limiteOcupacao = 90;
        int vazio = -1;
        int[] tabela = new int[tamanho];
        int[] chavesInseridas = new int[limiteOcupacao];
        int[] colisoesPorChave = new int[limiteOcupacao];
        int ocupacao = 0;
        int totalColisoes = 0;
        Random random = new Random(1090);

        // Marca todas as posições da tabela como vazias.
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = vazio;
        }

        // Gera e insere chaves até atingir 90% de ocupação.
        for (int tentativa = 0; tentativa < tamanho; tentativa++) {
            if (ocupacao >= limiteOcupacao) {
                break;
            }

            int chave = random.nextInt(100000);
            int posicao = chave % tamanho;
            int colisoes = 0;

            while (tabela[posicao] != vazio) {
                colisoes++;
                totalColisoes++;
                posicao = (posicao + 1) % tamanho;
            }

            tabela[posicao] = chave;
            chavesInseridas[ocupacao] = chave;
            colisoesPorChave[ocupacao] = colisoes;
            ocupacao++;
        }

        // Identifica as três chaves com mais colisões durante a inserção.
        int maior1 = -1;
        int maior2 = -1;
        int maior3 = -1;
        int elemento1 = vazio;
        int elemento2 = vazio;
        int elemento3 = vazio;

        for (int i = 0; i < ocupacao; i++) {
            int colisoes = colisoesPorChave[i];
            int chave = chavesInseridas[i];

            if (colisoes > maior1) {
                maior3 = maior2;
                elemento3 = elemento2;
                maior2 = maior1;
                elemento2 = elemento1;
                maior1 = colisoes;
                elemento1 = chave;
            } else if (colisoes > maior2) {
                maior3 = maior2;
                elemento3 = elemento2;
                maior2 = colisoes;
                elemento2 = chave;
            } else if (colisoes > maior3) {
                maior3 = colisoes;
                elemento3 = chave;
            }
        }

        // Exibe os resultados da inserção e as posições ainda livres.
        System.out.println("Elementos inseridos: " + ocupacao);
        System.out.println("Inserções não realizadas por causa do limite de 90%: " + (tamanho - ocupacao));
        System.out.println("Total de colisões na inserção: " + totalColisoes);
        System.out.println("3 elementos com mais colisões:");
        System.out.println("1º: " + elemento1 + " (colisões: " + maior1 + ")");
        System.out.println("2º: " + elemento2 + " (colisões: " + maior2 + ")");
        System.out.println("3º: " + elemento3 + " (colisões: " + maior3 + ")");

        System.out.print("Posições livres na matriz: ");
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] == vazio) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
