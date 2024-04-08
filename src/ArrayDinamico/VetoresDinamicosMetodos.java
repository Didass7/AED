import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VetoresDinamicosMetodos {

    private int nElementos;
    private String[] buffer;
    private int capMaxima;
    private int incremento;

    public VetoresDinamicosMetodos() {
        this(1000, 500); // capacidade inicial 1000 e incremento 500
    }

    public VetoresDinamicosMetodos(int capacidadeInicial) {
        this(capacidadeInicial, capacidadeInicial / 2 + 1);
        // capacidade inicial e incremento metade da capacidade inicial
    }

    public VetoresDinamicosMetodos(int capacidadeInicial, int incrementar) {
        if (capacidadeInicial <= 0) {
            capacidadeInicial = 1;
        }

        if (incrementar <= 0) {
            incrementar = capacidadeInicial / 2 + 1;
        }

        nElementos = 0;
        buffer = new String[capacidadeInicial];
        capMaxima = capacidadeInicial;
        incremento = incrementar;
    }

    // Método para ler palavras de um arquivo
    private void lerPalavrasDoArquivo() {
        String linha;
        Scanner leitor = null;
        File file = new File("C:\\Users\\diogo\\Downloads\\palavras.txt");

        try {
            leitor = new Scanner(file);
            while (leitor.hasNextLine()) {
                linha = leitor.nextLine();
                if (nElementos == capMaxima) {
                    aumentarVetor();
                }
                buffer[nElementos++] = linha;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }
    }

    // Método para aumentar o tamanho do vetor
    private void aumentarVetor() {
        int expansaoVetor = capMaxima + incremento;
        String[] novoBuffer = new String[expansaoVetor];
        for (int i = 0; i < capMaxima; i++) {
            novoBuffer[i] = buffer[i];
        }
        buffer = novoBuffer;
        capMaxima += incremento;
    }

    // Método para encontrar uma palavra no vetor
    public int encontrarPalavra(String temp, int posInicial) {
        for (int i = posInicial; i < nElementos; i++) {
            if (buffer[i].equals(temp)) {
                return i;
            }
        }
        return -1;
    }

    // Método para remover uma palavra do vetor
    public void removerPalavra(String temp) {
        int posicao = encontrarPalavra(temp, 0);

        if (posicao != -1) {
            for (int i = posicao; i < nElementos - 1; i++) {
                buffer[i] = buffer[i + 1];
            }
            buffer[nElementos - 1] = null;
            nElementos--;
        }
    }

    // Método para exibir o menu de opções
    public static String exibirMenu() {
        return "1 – Inserir elementos\n"
                + "2 – Encontrar uma palavra\n"
                + "3 – Remover um elemento\n"
                + "0 – Terminar";
    }

    public static void main(String[] args) {
        System.out.println("Bem-vindo!");
        System.out.println("Escolha uma opção:");

        VetoresDinamicosMetodos VetoresDinamicosMetodos = new VetoresDinamicosMetodos();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        VetoresDinamicosMetodos vetores = null;
        do {
            System.out.println(exibirMenu());
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    long inicio1 = System.nanoTime();
                    vetores.lerPalavrasDoArquivo();
                    long fim1 = System.nanoTime();
                    long tempoLer = fim1 - inicio1;
                    System.out.println("Tempo para ler as palavras: " + tempoLer + " nanosegundos.\n");
                    break;
                case 2:
                    System.out.println("Insira a palavra a ser encontrada:");
                    String palavra = scanner.next();
                    long inicio2 = System.nanoTime();
                    int posicao = vetores.encontrarPalavra(palavra, 0);
                    long fim2 = System.nanoTime();
                    long tempoEncontrar = fim2 - inicio2;
                    if (posicao != -1) {
                        System.out.println("Palavra encontrada na posição " + posicao);
                    } else {
                        System.out.println("Palavra não encontrada");
                    }
                    System.out.println("Tempo para encontrar a palavra: " + tempoEncontrar + " nanosegundos.\n");
                    break;
                case 3:
                    System.out.println("Insira a palavra a ser removida:");
                    String palavraRemover = scanner.next();
                    long inicio3 = System.nanoTime();
                    vetores.removerPalavra(palavraRemover);
                    long fim3 = System.nanoTime();
                    long tempoRemover = fim3 - inicio3;
                    System.out.println("Tempo para remover a palavra: " + tempoRemover + " nanosegundos.\n");
                    break;
                case 0:
                    System.out.println("Até à próxima!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }
}
