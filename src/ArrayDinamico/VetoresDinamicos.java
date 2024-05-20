package ArrayDinamico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VetoresDinamicos {


    private String[] elementos; //array que armazena as palavras
    private int tamanho; // tamanho do vetor
    private static final int CAPACIDADE_INICIAL = 10; //capacidade inicial do vetor

    //construtor
    public VetoresDinamicos() {
        this.elementos = new String[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }

    //metodo para adicionar elementos ao vetor
    public void adicionar(String valor) {
        if (tamanho == elementos.length) { //verifica se o vetor esta cheio
            aumentarCapacidade(); //aumenta a capacidade se estiver cheio
        }
        elementos[tamanho++] = valor; // adiciona a palavra ao fim da lista
    }

    //metodo para encontrar a posição de uma palavra na lista
    public int indiceDaPalavra(String valor) {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].equals(valor)) { //compara 1 a 1 cada elemento da lista com a string
                return i;
            }
        }
        return -1; //se não encontrar vai retornar "-1"
    }

    //metodo para remover palavras da lista
    public void remover(String valor) {
        int indice = indiceDaPalavra(valor); //variavel para encontrar o indice da palavra na lista
        //se o elemento existir no vetor, move os elementos para preencher o espaço deixado
        if (indice != -1) {
            for (int i = indice; i < tamanho - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            tamanho--; //diminui o tamanho do vetor
        }
    }

    //metodo para aumentar a capacidade do vetor
    private void aumentarCapacidade() {
        int novaCapacidade = elementos.length * 2; //dobra a capacidade atual
        String[] novoArray = new String[novaCapacidade]; //cria um novo array com a nova capacidade
        for (int i = 0; i < tamanho; i++) { // copia os elementos do vetor original para o novo vetor
            novoArray[i] = elementos[i];
        }
        elementos = novoArray; // atualiza a referência do vetor original para o novo vetor com maior capacidade
    }

    public static void main(String[] args) {

        System.out.println("Bem-vindo!");
        System.out.println("Escolha uma opção:");

        VetoresDinamicos vetor = new VetoresDinamicos();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        long inicio1, fim1, tempoLer, inicio2, fim2, tempoEncontrar, inicio3, fim3, tempoEliminar;
        do {
            System.out.println(menuInicial());
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    inicio1 = System.nanoTime();
                    tempoDeExecucaoLerPalavras(vetor);
                    fim1 = System.nanoTime();
                    tempoLer = fim1 - inicio1;
                    System.out.println("A tarefa demorou " + tempoLer + " nanosegundos a  ler a Lista de Palavras\n");
                    break;
                case 2:
                    tempoDeExecucaoEncontrarPalavra(vetor);
                    break;
                case 3:
                    inicio3 = System.nanoTime();
                    tempoDeExecucaoEliminarPalavra(vetor);
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

    private static String menuInicial() {
        return "1 – Inserir elementos\n"
                + "2 – Encontrar uma palavra\n"
                + "3 – Apagar um elemento\n"
                + "0 – Terminar";
    }

    //metodo para introduzar o ficheiro palavras.txt no programa
    private static void tempoDeExecucaoLerPalavras(VetoresDinamicos vetor) {
        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");

        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                vetor.adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }
    }

    //metodo para encontrar uma palavra
    private static void tempoDeExecucaoEncontrarPalavra(VetoresDinamicos vetor) {
        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");

        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                vetor.adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }

        String[] palavras = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};

        //ciclo para encontrar cada palavra e medir o tempo que demorou a executar essa tarefa
        for (String palavra : palavras) {
            long inicio = System.nanoTime();
            int indice = vetor.indiceDaPalavra(palavra);
            long fim = System.nanoTime();
            long duration = fim - inicio;
            if (indice != -1) {
                System.out.println("A palavra '" + palavra + "' está na posição: " + indice + " Tempo de execução: " + duration);
            } else {
                System.out.println("A palavra '" + palavra + "' não foi encontrada. Tempo de execução: " + duration);
            }
        }
    }

    //metodo para medir quanto tempo demora a eleminar uma palavra
    private static void tempoDeExecucaoEliminarPalavra(VetoresDinamicos vetor) {

        String tmp;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");

        try (Scanner leitor = new Scanner(file)) {
            while (leitor.hasNextLine()) {
                tmp = leitor.nextLine();
                vetor.adicionar(tmp);
            }
            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
            e.printStackTrace();
        }

        String[] palavras = {"trazemos", "intulhos", "glosa",
                "assombrados", "sinceramente", "urubu", "fumou", "aope", "viçosos", "repercurtir"};

        //ciclo para encontrar quanto tempo demorou para remover cada palavra individualmente
        for (String palavra : palavras) {
            long inicio = System.nanoTime();
            vetor.remover(palavra);
            long fim = System.nanoTime();
            long duration = fim - inicio;
            System.out.println("Palavra '" + palavra + "' removida em " + duration + " nanosegundos");
        }
    }
}


