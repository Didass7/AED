package ListasInterligadas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListasInterligadas {

    //Nó inicial da lista
    private static No inicio;

    //Defenição da classe nó
    private static class No {
        String valor;
        No proximo;

        //Construtor do nó
        public No(String valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    //método para adicionar uma palavra à lista
    public static void adicionar(String valor) {
        //se a lista estiver vazia cria o primeiro nó
        if (inicio == null) {
            inicio = new No(valor);
        } else {
            No temp = inicio;
            while (temp.proximo != null) {
                temp = temp.proximo;
            }
            temp.proximo = new No(valor); //adiciona um novo nó ao final
        }
    }

    public static int indiceDaPalavra(String valor) {
        int index = 0;
        No temp = inicio;
        while (temp != null) {
            if (temp.valor.equals(valor)) {
                return index;

                //retorna o indice da palavra até
            }
            temp = temp.proximo;
            index++;
        }
        return -1;
    }

    public static void remover(String valor) {
        No anterior = null;
        No atual = inicio;

        while (atual != null) {
            if (atual.valor.equals(valor)) {
                if (anterior == null) { // se o nó a ser removido for o primeiro
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return; // sai do método após remover o nó
            }
            anterior = atual;
            atual = atual.proximo;
            // avança para o próximo nó
        }
    }

    public static boolean contains(String palavra) {
        No temp = inicio;
        while (temp != null) {
            if (temp.valor.equals(palavra)) {
                return true; // se encontrarmos a palavra, retorna true
            }
            temp = temp.proximo;
        }
        return false; // se não encontrarmos a palavra, retorna false
    }


    public static String menuInicial() {
        return "1 – Inserir elementos\n"
                + "2 – Encontrar uma palavra\n"
                + "3 – Apagar um elemento\n"
                + "0 – Terminar";
    }

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
                    tempoDeExecucaoEncontrarPalavra();
                    break;
                case 3:
                    tempoDeExecucaoEliminarPalavra();
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

    // Quanto tempo demora a ler as palavras
    private static void tempoDeExecucaoLerPalavras() {
        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");

        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                // Adiciona as palavra à lista
                adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }
    }
    //Quanto tempo demora a encontrar uma palavra
    private static void tempoDeExecucaoEncontrarPalavra() {
        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");
        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                // Adiciona as palavra à lista
                adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }


        String[] palavras = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};

        //ciclo para encontrar quanto tempo demorou para encontrar cada palavra individualmente
        for (String palavra : palavras) {
            long inicio = System.nanoTime();
            int posicao = indiceDaPalavra(palavra);
            long fim = System.nanoTime();
            long tempo = fim - inicio;
            if (posicao != -1) { //se a palavra for diferente de -1 (sendo -1 significado de não encontrada),
                // informa a sua posição e o tempo que demorou a encontrar cada palavra
                System.out.println("A posição da palavra '" + palavra + "' é: " + posicao);
                System.out.println("Tempo para encontrar a palavra '" + palavra + "': " + tempo + " nanosegundos\n");
            } else {
                System.out.println("A palavra '" + palavra + "' não foi encontrada na lista.");
            }
        }
    }
    //Quanto tempo demora a remover uma palavra
    private static void tempoDeExecucaoEliminarPalavra() {
        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");
        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                // Adiciona as palavra à lista
                adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }

        String[] palavras = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};

        //ciclo para encontrar quanto tempo demorou para encontrar cada palavra individualmente
        for (String palavra : palavras) {
            if (contains(palavra)) {
                long inicio = System.nanoTime();
                remover(palavra);
                long fim = System.nanoTime();
                long tempo = fim - inicio;
                System.out.println("Palavra '" + palavra + "' removida com sucesso!");
                System.out.println("Tempo para remover a palavra '" + palavra + "': " + tempo + " nanosegundos\n");
            } else {
                System.out.println("A palavra '" + palavra + "' não está na lista e não pode ser removida.");
            }
        }
    }
}