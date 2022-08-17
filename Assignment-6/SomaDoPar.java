import java.util.ArrayList;
import java.util.HashSet;

public class SomaDoPar {

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros uma lista de inteiros
     * @param somaDesejada a soma desejada
     *
     * @return O menor dos elementos do par encontrado;
     *         ou null, caso nenhum par de elementos some o valor desejado
     */
    public static Integer encontrarParComSomaDadaQuadratica(ArrayList<Integer> numeros, int somaDesejada) {

        //Algoritmo ineficiente(quadrático):

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x, y);
                }
            }

        }
        return null;
    }
        // Algoritmo eficiente (linear):

        public static Integer encontrarParComSomaDadaLinear(ArrayList<Integer> numeros, int somaDesejada){

            HashSet<Integer> novoSet = new HashSet();

            int numero = 0;

            for(Integer i : numeros ){

                novoSet.add(i);


            }
            for(Integer i : novoSet){

                numero = somaDesejada - i;
                if(novoSet.contains(numero)){
                    return Math.min(i,numero);
                }

            }
            return null;
        }

    }

