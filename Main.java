import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
      
        char operacao = 'S'; 

      
        int[][] matriz = lerMatrizDoArquivo("data/matriz.json");

       
        imprimirMatrizDestacada(matriz);

      
        double resultado = calcularResultado(operacao, matriz);

      
        System.out.printf("%.1f\n", resultado);
    }

    public static int[][] lerMatrizDoArquivo(String nomeArquivo) throws IOException {
        List<String> linhas = Files.readAllLines(new File(nomeArquivo).toPath());
        int tamanho = linhas.size();
        int[][] matriz = new int[tamanho][];

        for (int i = 0; i < tamanho; i++) {
            String[] elementos = linhas.get(i).split(",");
            matriz[i] = new int[elementos.length];
            for (int j = 0; j < elementos.length; j++) {
                matriz[i][j] = Integer.parseInt(elementos[j]);
            }
        }

        return matriz;
    }

    public static double calcularResultado(char operacao, int[][] matriz) {
        int soma = 0;
        int contador = 0;

        int tamanho = matriz.length;

        for (int i = 0; i < tamanho; i++) {
            for (int j = tamanho / 2 + 1; j < tamanho - 1 - i; j++) {
                soma += matriz[i][j];
                contador++;
            }
        }

        if (operacao == 'S') {
            return soma;
        } else if (operacao == 'M') {
            return (double) soma / contador;
        }

        return 0; 
    }

    public static void imprimirMatrizDestacada(int[][] matriz) {
        int tamanho = matriz.length;

        System.out.println("Matriz com os elementos da área direita destacados:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (j > tamanho / 2) {
                    System.out.print("* ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
