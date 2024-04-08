package ArrayFixo;

import java.io.*;
import java.util.Scanner;

public class ArrayFixo {

    public static String menuInicial() {
        return "1 – Inserir elementos\n" + "2 – Encontrar uma palavra\n" + "3 – Apagar um elemento\n" + "0 – Terminar";
    }

    String[] tmp;
    int tamanhoAtual;
    private static String[] palavras = new String[30000];

    public static void main(String[] args) {


        System.out.println("Bem-vindo!");
        System.out.println("Escolha uma opção:");


        long inicio1, fim1, tempoLer, inicio2, fim2, tempoEncontrar, inicio3, fim3, tempoEliminar;
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println(menuInicial());
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    inicio1 = System.nanoTime();
                    tempoDeExecucaoLerPalavras();
                    fim1 = System.nanoTime();
                    tempoLer = fim1 - inicio1;
                    System.out.println("A tarefa demorou " + tempoLer + " nanosegundos a  ler a Lista de Palavras\n");
                    break;
                case 2:
                    inicio2 = System.nanoTime();
                    tempoDeExecucaoEncontrarPalavra();
                    fim2 = System.nanoTime();
                    tempoEncontrar = fim2 - inicio2;
                    System.out.println("A tarefa demorou " + tempoEncontrar + " nanosegundos a encontrar uma Palavra\n");
                    break;
                case 3:
                    inicio3 = System.nanoTime();
                    tempoDeExecucaoEliminarPalavra();
                    fim3 = System.nanoTime();
                    tempoEliminar = fim3 - inicio3;
                    System.out.println("A tarefa demorou " + tempoEliminar + " nanosegundos a eliminar uma Palavra\n");
                    break;
                case 0:
                    System.out.println("Até à proxima!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private static void tempoDeExecucaoLerPalavras() {
        String[] arrayPalavras = new String[30000];
        int contador = 0;
        File file = new File("D:\\Desktop\\mods\\palavras.txt");

        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                arrayPalavras[contador++] = leitor.nextLine();
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }
    }


    private static void tempoDeExecucaoEncontrarPalavra() {
        String[] arrayPalavras = new String[30000];
        int contador = 0;
        File file = new File("D:\\Desktop\\mods\\palavras.txt");
        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                arrayPalavras[contador++] = leitor.nextLine();
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }

        String[] palavrasProcuradas = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};
        for (String palavra : palavrasProcuradas) {
            int posicao = -1;
            for (int i = 0; i < contador; i++) {
                if (arrayPalavras[i].equals(palavra)) {
                    posicao = i;
                    break;
                }
            }
            System.out.println("A posição da palavra '" + palavra + "' é: " + posicao);
        }
    }


    private static void tempoDeExecucaoEliminarPalavra() {
        String[] arrayPalavras = new String[30000];
        int contador = 0;
        File file = new File("D:\\Desktop\\mods\\palavras.txt");
        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                arrayPalavras[contador++] = leitor.nextLine();
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }

        String[] palavrasRemover = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};
        for (String palavra : palavrasRemover) {
            long inicio = System.nanoTime();
            boolean palavraRemovida = false;
            for (int i = 0; i < contador; i++) {
                if (arrayPalavras[i].equals(palavra)) {
                    arrayPalavras[i] = null;
                    palavraRemovida = true;
                    break;
                }
            }
            long fim = System.nanoTime();
            long tempo = fim - inicio;
            if (palavraRemovida) {
                System.out.println("Palavra '" + palavra + "' removida com sucesso!");
                System.out.println("Tempo para remover a palavra '" + palavra + "': " + tempo + " nanosegundos\n");
            } else {
                System.out.println("A palavra '" + palavra + "' não está na lista e não pode ser removida.");
            }
        }
    }
}



