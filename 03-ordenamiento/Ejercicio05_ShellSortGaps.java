/*
 * ============================================================================
 * UNIVERSIDAD INSTITUTO UNIVERSITARIO AERONÁUTICO
 * Estructuras de Datos y Algoritmos
 * Alumno: Petrangeli Dylan
 * Práctico 3: Algoritmos de Ordenamiento
 * Ejercicio: 05
 * ============================================================================
 * PROMPT INICIAL UTILIZADO EN OPENCODE:
 * "Desarrollá una implementación en Java del algoritmo ShellSort para ordenar un arreglo de números enteros desordenados ingresado como dato de entrada. El razonamiento de ShellSort radica en ser una evolución de Insertion Sort que realiza comparaciones e intercambios mediante saltos o intervalos decrecientes llamados 'gaps'. El programa debe imprimir por pantalla el valor del 'gap' utilizado en cada fase de la secuencia decreciente y el estado completo del arreglo tras finalizar cada pasada. Incluí comentarios teóricos en el código que expliquen por qué ShellSort mejora sustancialmente la eficiencia de Insertion Sort, justificando cómo los saltos iniciales largos mueven rápidamente los elementos desordenados a grandes distancias y cómo al llegar al 'gap = 1' el arreglo se encuentra casi ordenado reduciendo los desplazamientos a un mínimo. Para verificar el resultado, el programa debe mostrar la transición ordenada progresiva en cada etapa hasta la consolidación del arreglo ordenado final."
 * ============================================================================
 */
import java.util.Arrays;

public class Ejercicio05_ShellSortGaps {
    public static void main(String[] args) {
        int[] numeros = {35, 12, 43, 8, 51, 2, 19, 27};
        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        shellSort(numeros);
        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        if (!estaOrdenado(numeros)) throw new IllegalStateException("Shell Sort no ordenó el arreglo.");
    }

    public static void shellSort(int[] numeros) {
        for (int gap = numeros.length / 2; gap > 0; gap /= 2) {
            // La inserción con saltos largos desplaza elementos lejos con rapidez.
            // Al llegar a gap=1, los elementos suelen estar casi ubicados y hacen
            // falta pocos desplazamientos; esta última fase es insertion sort ordinario.
            for (int i = gap; i < numeros.length; i++) {
                int valor = numeros[i];
                int j = i;
                while (j >= gap && numeros[j - gap] > valor) {
                    numeros[j] = numeros[j - gap];
                    j -= gap;
                }
                numeros[j] = valor;
            }
            System.out.println("Gap " + gap + ": " + Arrays.toString(numeros));
        }
    }

    private static boolean estaOrdenado(int[] numeros) {
        for (int i = 1; i < numeros.length; i++) if (numeros[i - 1] > numeros[i]) return false;
        return true;
    }
}
