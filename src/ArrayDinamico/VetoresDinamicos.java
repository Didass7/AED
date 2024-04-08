import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VetoresDinamicos {


    private String[] elementos;
    private int tamanho;
    private static final int CAPACIDADE_INICIAL = 10;

    public VetoresDinamicos() {
        this.elementos = new String[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }

    public void adicionar(String valor) {
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }
        elementos[tamanho++] = valor;
    }

    public int indexOf(String valor) {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public void remover(String valor) {
        int indice = indexOf(valor);
        if (indice != -1) {
            for (int i = indice; i < tamanho - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            tamanho--;
        }
    }

    private void aumentarCapacidade() {
        int novaCapacidade = elementos.length * 2;
        String[] novoArray = new String[novaCapacidade];
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = elementos[i];
        }
        elementos = novoArray;
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
                    inicio2 = System.nanoTime();
                    tempoDeExecucaoEncontrarPalavra(vetor);
                    fim2 = System.nanoTime();
                    tempoEncontrar = fim2 - inicio2;
                    System.out.println("A tarefa demorou " + tempoEncontrar + " nanosegundos a encontrar uma Palavra\n");
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

        }while (opcao != 0) ;
    }
    private static String menuInicial() {
        return "1 – Inserir elementos\n"
                + "2 – Encontrar uma palavra\n"
                + "3 – Apagar um elemento\n"
                + "0 – Terminar";
    }

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

    private static void tempoDeExecucaoEncontrarPalavra(VetoresDinamicos vetor) {
        System.out.println("Digite a palavra a ser encontrada:");
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();
        int indice = vetor.indexOf(palavra);
        if (indice != -1) {
            System.out.println("A palavra está na posição: " + indice);
        } else {
            System.out.println("A palavra não foi encontrada.");
        }
    }

    private static void tempoDeExecucaoEliminarPalavra(VetoresDinamicos vetor) {
        System.out.println("Digite a palavra a ser removida:");
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();
        vetor.remover(palavra);
        System.out.println("Palavra removida com sucesso!");
    }
}

