import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        ArvoreRubroNegra arvoreRN = new ArvoreRubroNegra();
        long inicioAVL, fimAVL, inicioRN, fimRN;

        int[] array = extrairDados("src/numeros_100000.txt");
        if(array != null){
            System.out.println(array.length);

            inicioAVL = System.currentTimeMillis();
            AlgoritmoAVL(arvoreAVL, array);
            fimAVL = System.currentTimeMillis();

            System.out.println("Tempo de Execução da Arvore AVL: "+ calcTempo(fimAVL - inicioAVL)+ "\n\n");



            inicioRN = System.currentTimeMillis();
            AlgoritmoRN(arvoreRN, array);
            fimRN = System.currentTimeMillis();
            System.out.printf("Tempo de Execução da Arvore Rubro Negra: "+ calcTempo(fimRN - inicioRN)+ "\n\n");
        }
        else{
            System.out.println("Array não encontrado");
        }
    }

    public static void AlgoritmoAVL(ArvoreAVL arvore, int[] array){
        Random random = new Random();
        int min = -9999;
        int max = 9999;

        System.out.println("==> Inserindo dados na Arvore AVL");
        for(int i=0; i < array.length; i++){
            arvore.setRaiz(array[i]);
        }
        System.out.println("==> numeros inseridos na Arvore AVL");

        System.out.println("==> Operações na Arvore AVL");
        for(int i=0; i < 50000; i++){
            arvore.setRaiz(array[i]);
            int numero = random.nextInt((max - min) + 1) + min;
            if(multiplo3(numero) || multiplo5(numero)){
                arvore.remocao(numero);
            }
            else{
                arvore.realizarBuscar(numero);
            }
        }

    }

    public static void AlgoritmoRN(ArvoreRubroNegra arvore, int[] array){
        Random random = new Random();
        int min = -9999;
        int max = 9999;

        System.out.println("==> Inserindo dados na Arvore AVL");
        for(int i=0; i < array.length; i++){
            arvore.setRaiz(array[i]);
        }
        System.out.println("==> numeros inseridos na Arvore Rubro-Negra");

        System.out.println("==> Operações na Arvore Rubro-Negra");
        for(int i=0; i < 50000; i++){
            arvore.setRaiz(array[i]);
            int numero = random.nextInt((max - min) + 1) + min;
            if(multiplo3(numero) || multiplo5(numero)){
                arvore.remover(numero);
            }
            else{
                arvore.buscar(numero);
            }
        }
    }

    public static int[] extrairDados(String url) throws IOException {
        Path arquivo = Path.of(url);

        if (Files.notExists(arquivo)) {
            return null;
        } else {
            String valoresString = Files.readString(arquivo);
            String formataValor = valoresString.replace("[", "").replace("]", "").replace(" ", "");

            String[] arrayString = formataValor.split(",");

            int[] arrayInt = new int[arrayString.length];

            for (int i = 0; i < arrayString.length; i++) {
                arrayInt[i] = Integer.valueOf(arrayString[i]);
            }

            return arrayInt;
        }
    }

    public static String calcTempo(long total) {
        long opHr, opMin, opSeg, opMils;

        opMils = total % 1000;
        total /= 1000;
        opHr = total / 3600;
        total %= 3600;
        opMin = total / 60;
        opSeg = total % 60;

        return opHr + ":" + opMin + ":" + opSeg + ":" + opMils;
    }

    public static boolean multiplo3(int num){
        return num % 3 == 0;
    }

    public static boolean multiplo5(int num){
        return num % 5 == 0;
    }
}
